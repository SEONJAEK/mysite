### Mysite04, 05 Package Structure
<pre>
[src]
   |--- [main]
                 |--- [java]
                 |	         |--- com 
                 |	         |		|--- poscoict
                 |	         |		|	|--- config
                 |	         |		|	|		|--- app
                 |	         |		|	|		|		|--- DBConfig.java		// jdbc.properties에서 실제 DB 데이터 가지고 와서 세팅해주는 곳(틀)
                 |	         |		|	|		|		|--- MyBatisConfig.java // sqlSessionFactory에 빈객체를 생성하고, db sql을 java와 연결해주는 곳: configuration xml에서 실제 db에 관한 정보를 가져온다. 
                 |	         |		|	|		|--- web
                 |	         |		|	|		|		|--- MVCConfig.java
                 |	         |		|	|		|		|--- SecurityConfig.java
                 |	         |		|	|		|		|--- MessageConfig.java
                 |	         |		|	|		|		|--- FileuploadConfig.java
                 |	         |		|	|--- mysite
                 |	         |		|	|		|--- config
                 |	         |		|	|		|		|--- AppConfig.java
                 |	         |		|	|		|		|--- WebConfig.java
                 |	         |		|	|		|--- controller
                 |	         |		|	|		|--- service
                 |	         |		|	|		|--- repository
                 |	         |		|	|		|--- vo
                 |	         |		|	|		|--- exception
                 |	         |		|	|		|--- aop
                 |
                 |--- [resources]
                 |	         |--- logback.xml	
                 |	         |--- com 
                 |	         |		|--- poscoict
                 |	         |		|		|--- mysite
                 |	         |		|		|	|--- config
                 |	         |		|		|	|		|--- app
                 |	         |		|		|	|		|		|-- jdbc.properties
                 |	         |		|		|	|		|		|-- mybatis  
                 |	         |		|		|	|		|		|        |-- configuration.xml               
                 |	         |		|		|	|		|		|        |-- mappers                                
                 |	         |		|		|	|		|		|        |      |-- board.xml                               
                 |	         |		|		|	|		|		|        |      |-- user.xml                               
                 |	         |		|		|	|		|--- web
<pre>                 