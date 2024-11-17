CREATE TABLE client(
    id bigserial primary key ,
    email text not null unique ,
    firstName text not null  ,
    lastName text not null  ,
    gender text not null ,
    birth_date text not null ,
    phone_number bigint not null ,

    country text not null,
    state text not null ,
    city text not null ,
    postal_code bigint not null ,
    street text not null ,

    age_risk text not null ,
    health_risk text not null ,
    job_risk text not null ,
    living_area_risk text not null ,

    price bigint
)

