import random

# f = open("/Users/mac_1/data/sql/test.sql",'w')
f = open("/Users/mac_1/data/sql/test1.sql",'w')


for i in range(200005,500000):
    groupId=random.randint(1,8)
    hasread=random.randint(0,1)
    sql ='insert into study.notice(userId,hasRead, groupId, id) values (''1238'', '+str(hasread)+', '+str(groupId)+', '+str(i)+');'
    f.write(sql)
    f.write("\n")

print(str(i)+'+ success!')
f.close()
