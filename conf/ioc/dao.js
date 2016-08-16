/**
 * Created by hocgin on 16-6-29.
 */
var daoIOC = {
    dataSource : {
        type : "com.alibaba.druid.pool.DruidDataSource",
        events : {
            create : "init",
            depose : 'close'
        },
        fields : {
            url : "jdbc:mysql://127.0.0.1:3306/websocket?characterEncoding=UTF-8",
            username : "root",
            password : "hocgin",
            testWhileIdle : true,
            validationQuery : "select 1" ,
            maxActive : 100
        }
    },
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    }
};