package com.example.springaop.audit;


import com.alibaba.fastjson.JSONObject;
import com.example.springaop.filter.HttpServletContent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zds
 * @Description 源码
 * @createTime 2021/10/18 18:16
 */

@Aspect
@Component
public class AuditAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogNameEnum.AUDITLOG.getLogName());

//    @Autowired
//    private SnowflakeIdWorker worker;

    @Value("${spring.application.name:TestAop}")
    private String applicationName;

    ThreadLocal<String> operateTime = new ThreadLocal<String>();

    ThreadLocal<Long> traceId = new ThreadLocal<Long>();

    ThreadLocal<String> operator = new ThreadLocal<>();
    // IP地址
    ThreadLocal<String> ip = new ThreadLocal<>();

    ThreadLocal<String> url = new ThreadLocal<>();

    ThreadLocal<String> operate_object = new ThreadLocal<>();
    // 操作信息实体
    ThreadLocal<AuditMessage> msg = new ThreadLocal<>();

    private static final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    private static final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();


    // 声明AOP切入点
    @Pointcut("@annotation(com.example.springaop.audit.Audit)")
    public void audit() {
    }

    @Pointcut("@annotation(com.example.springaop.audit.AuditFile)")
    public void importFile() {
    }

    @Before("audit()")
    public void beforeExec(JoinPoint joinPoint) {

    }

    /**
     *    ①Spring4.0
     * 　　　　正常情况：环绕前置=====@Before======目标方法执行=====环绕返回=====环绕最终=====@After=====@AfterReturning
     * 　　　　异常情况：环绕前置=====@Before======目标方法执行=====环绕异常=====环绕最终=====@After=====@AfterThrowing
     * 　　②Spring5.28
     * 　　　　正常情况：环绕前置=====@Before=====目标方法执行=====@AfterReturning=====@After=====环绕返回=====环绕最终
     * 　　　　异常情况：环绕前置=====@Before=====目标方法执行=====@AfterThrowing=====@After=====环绕异常=====环绕最终
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("audit()")
    public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable {
        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletRequest request = HttpServletContent.getRequest();
            operateTime.set(String.valueOf(System.currentTimeMillis()));
//            traceId.set();
            ip.set(getIpAddr(request));
            url.set(request.getRequestURI());
//            operator.set(getOperator(request));
            operate_object.set(getNameAndValue(pjp));
            MethodSignature ms = (MethodSignature) pjp.getSignature();
            Method method = ms.getMethod();
            Audit auditAnno = method.getAnnotation(Audit.class);
            AuditMessage auditMessage = new AuditMessage();
            // Spel表达式解析日志信息
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            if (parameterNames != null && parameterNames.length > 0) {
                EvaluationContext context = new StandardEvaluationContext();

                //获取方法参数值
                Object[] args = pjp.getArgs();
                for (int i = 0; i < args.length; i++) {
                    context.setVariable(parameterNames[i], args[i]);
                }
                ParserContext parserContext = new ParserContext() {

                    @Override
                    public boolean isTemplate() {
                        return true;
                    }

                    @Override
                    public String getExpressionPrefix() {
                        return "${";
                    }

                    @Override
                    public String getExpressionSuffix() {
                        return "}";
                    }
                };
                String opeationInfo = spelExpressionParser.parseExpression(auditAnno.operation(),parserContext).getValue(context).toString();
                auditMessage.setOperation(opeationInfo);
            }
            auditMessage.setTraceId(traceId.get());
            auditMessage.setAccessor_ip(ip.get());
            auditMessage.setOperate_type(auditAnno.operate_type());
            auditMessage.setOperate_time(operateTime.get());
            auditMessage.setService_name(applicationName);
            auditMessage.setOperate_url(url.get());
            auditMessage.setOperator(operator.get());
            auditMessage.setOperate_object(operate_object.get());
            msg.set(auditMessage);
        } catch (Exception e) {
            LOGGER.error("Error occured while auditing, cause by: ", e);
        }
        Object rtn = pjp.proceed();
        return rtn;
    }


    @Around("importFile()")
    public Object aroundExecFile(ProceedingJoinPoint pjp) throws Throwable {
        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            HttpServletRequest request = HttpServletContent.getRequest();
            operateTime.set(String.valueOf(System.currentTimeMillis()));
//            traceId.set(worker.nextId());
            ip.set(getIpAddr(request));
            url.set(request.getRequestURI());
//            operator.set(getOperator(request));
            operate_object.set(getMultipartFileValue(pjp,request));
            MethodSignature ms = (MethodSignature) pjp.getSignature();
            Method method = ms.getMethod();
            AuditFile auditAnno = method.getAnnotation(AuditFile.class);
            AuditMessage auditMessage = new AuditMessage();
            auditMessage.setOperation(auditAnno.operation());
            auditMessage.setTraceId(traceId.get());
            auditMessage.setAccessor_ip(ip.get());
            auditMessage.setOperate_type(auditAnno.operate_type());
            auditMessage.setOperate_time(operateTime.get());
            auditMessage.setService_name(applicationName);
            auditMessage.setOperate_url(url.get());
            auditMessage.setOperator(operator.get());
            auditMessage.setOperate_object(operate_object.get());
            msg.set(auditMessage);
        } catch (Exception e) {
            LOGGER.error("Error occured while auditing, cause by: ", e);
        }
        Object rtn = pjp.proceed();
        return rtn;
    }

    /**
     * 带参返回
     */
    @AfterReturning(pointcut = "audit()", returning = "rc")
    public void doAfterReturning(Object rc) {
        afterReturning(rc);
    }

    /**
     * File带参返回
     */
    @AfterReturning(pointcut = "importFile()", returning = "rc")
    public void doAfterFileReturning(ResponseEntity<ApiResponse> rc) {
        afterReturning(rc);
    }

    private void afterReturning(Object rc) {
        if (rc != null && rc instanceof ResponseEntity) {
            ResponseEntity<ApiResponse> result = (ResponseEntity<ApiResponse>) rc;
            if (ApiResponse.STATUS_OK.equals(result.getBody().getStatus())) {
                AuditMessage auditMessage = msg.get();
                auditMessage.setResult(ResponseContent.STATUS_OK);
                if (!CollectionUtils.isEmpty(result.getBody().getData())) {
                    auditMessage.setDetail(JSONObject.toJSONString(result.getBody().getData()));
                } else if (!CollectionUtils.isEmpty(result.getBody().getEntities())) {
                    auditMessage.setDetail(JSONObject.toJSONString(result.getBody().getEntities()));
                } else {
                    auditMessage.setDetail(JSONObject.toJSONString(result.getBody().getEntity()));
                }
                LOGGER.info("info:{}", JSONObject.toJSONString(auditMessage));
                return;
            }
            if (ApiResponse.STATUS_FAIL.equals(result.getBody().getStatus())) {
                AuditMessage auditMessage = msg.get();
                auditMessage.setResult(ResponseContent.STATUS_FAIL);
                auditMessage.setDetail(result.getBody().getErrorDescription());
                LOGGER.info("info:{}", JSONObject.toJSONString(auditMessage));
                return;
            }
        }
        if (rc != null) {
            AuditMessage auditMessage = msg.get();
            auditMessage.setResult(ResponseContent.STATUS_OK);
            auditMessage.setDetail(JSONObject.toJSONString(rc));
            LOGGER.info("info:{}", JSONObject.toJSONString(auditMessage));
            return;
        }
    }

    @AfterThrowing(pointcut = "importFile()", throwing = "e")
    public void doAfterFileThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            if (e != null) {
                AuditMessage auditMessage = msg.get();
                auditMessage.setResult(ResponseContent.STATUS_FAIL);
                auditMessage.setDetail(e.getMessage());
                LOGGER.info("info:{}", JSONObject.toJSONString(auditMessage));
            }
        } catch (Exception e1) {

        }
    }

    @AfterThrowing(pointcut = "audit()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            if (e != null) {
                AuditMessage auditMessage = msg.get();
                auditMessage.setResult(ResponseContent.STATUS_FAIL);
                auditMessage.setDetail(e.getMessage());
                LOGGER.info("info:{}", JSONObject.toJSONString(auditMessage));
            }
        } catch (Exception e1) {

        }
    }


    /**
     * 不带参返回
     */
    @AfterReturning(pointcut = "audit()")
    public void doAfterReturning(JoinPoint joinPoint) {

    }


    /**
     * 记录x-forwarded-for所有ip
     *
     * @return ip
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


//    public String getOperator(HttpServletRequest request) {
//        AuthenticateResponse authInfo = (AuthenticateResponse) request.getAttribute(SESSION_AUTH_INFO);
//        if (authInfo == null) {
//            return null;
//        }
//        AuthenticateResponse.User user = authInfo.getEntity();
//        if (user == null) {
//            return null;
//        }
//        return user.getUserId();
//    }


    /**
     * 获取参数Map集合
     *
     * @param joinPoint
     * @return
     */
    private String getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if (paramValues[i] instanceof ServletRequest) {
                continue;
            }
            if (paramValues[i] instanceof ServletResponse) {
                continue;
            }
            if (paramValues[i] instanceof MultipartFile) {
                continue;
            }
            param.put(paramNames[i], paramValues[i]);
        }
        if (!param.isEmpty()) {
            return JSONUtil.mapToJsonString(param);
        }
        return null;
    }


    private String getMultipartFileValue(ProceedingJoinPoint joinPoint,HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        for (int i = 0; i < paramValues.length; i++) {
            if (paramValues[i] instanceof MultipartFile) {
                MultipartFile multipartFile = (MultipartFile) paramValues[i];
                param.put("fileSize", multipartFile.getSize());
                param.put("fileName", multipartFile.getOriginalFilename());
                break;
            }
        }
        if (!param.isEmpty()) {
            return JSONUtil.mapToJsonString(param);
        }
        return null;
    }

}
