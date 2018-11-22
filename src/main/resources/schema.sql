CREATE TABLE stockprice
(
id integer not null,
date varchar2(30),
open double,
high double(255),
low double(255),
close double(255),

primary key(id)
)AS 
SELECT * 
FROM CSVREAD('C:\\Users\\1565359\\Documents\\SpringBootSampleCode\\KLMProject\\F1.csv');