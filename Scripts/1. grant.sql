SELECT USER FROM DUAL;

CREATE USER spring_mybatis IDENTIFIED BY rootroot;

GRANT CONNECT, RESOURCE, CREATE SYNONYM, CREATE VIEW
   TO spring_mybatis;
   
SELECT * 
  FROM DBA_USERS
 WHERE username = 'spring_mybatis';