CREATE table category(

id IDENTITY,
name VARCHAR(50),
description VARCHAR(255),
image_url varchar(100),
is_active boolean,

CONSTRAINT pk_category_id PRIMARY KEY (id)
);