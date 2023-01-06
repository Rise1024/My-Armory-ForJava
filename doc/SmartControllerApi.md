
# 
## 这是一个查询接口

**URL:** `http://{{server}}/smart`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 这是一个查询接口





**Request-example:**
```
curl -X GET -i http://{{server}}/smart
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|name|string|姓名|-|
|age|int32|年龄|-|
|boy|boolean|是否男孩|-|

**Response-example:**
```
{
  "name": "jenifer.hammes",
  "age": 30,
  "boy": true
}
```

## 这是一个创建接口

**URL:** `http://{{server}}/smart1`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 这是一个创建接口



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|name|string|false|姓名|-|
|age|int32|false|年龄|-|
|boy|boolean|false|是否男孩|-|


**Request-example:**
```
curl -X POST -i http://{{server}}/smart1 --data 'name=jenifer.hammes&age=30&boy=true'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|name|string|姓名|-|
|age|int32|年龄|-|
|boy|boolean|是否男孩|-|

**Response-example:**
```
{
  "name": "jenifer.hammes",
  "age": 30,
  "boy": true
}
```

