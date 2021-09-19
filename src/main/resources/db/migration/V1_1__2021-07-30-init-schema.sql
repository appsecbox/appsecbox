create table applications
(
    id uuid default random_uuid(),
    name varchar(1024),
    primary key (id)
);
create table applications_components
(
    application_id uuid,
    component_id uuid,
    primary key (application_id,component_id)
);
create table applications_meta
(
    application_id uuid,
    value varchar(1024),
    key varchar(2048),
    primary key (application_id,key)
);
create table assessments
(
    id uuid default random_uuid(),
    state varchar(128),
    time_start bigint,
    primary key (id)
);
create table components
(
    id uuid default random_uuid(),
    name varchar(1024),
    primary key (id)
);
create table components_meta
(
    component_id uuid,
    value varchar(1024),
    key varchar(2048),
    primary key (component_id,key)
);
create table components_technical_components
(
    component_id uuid,
    technical_component_id uuid,
    primary key (component_id,technical_component_id)
);
create table controls
(
    id uuid default random_uuid(),
    name varchar(1024),
    type varchar(1024),
    docker_command varchar(2048),
    primary key (id)
);
create table issues
(
    id uuid default random_uuid(),
    name varchar(1024),
    description varchar(2048),
    severity varchar(64),
    state varchar(64),
    job_id uuid,
    primary key (id)
);
create table issues_meta
(
    issue_id uuid,
    value varchar(1024),
    key varchar(2048),
    primary key (issue_id,key)
);
create table jobs
(
    id uuid default random_uuid(),
    meta varchar(1024),
    state varchar(64),
    control_id uuid,
    technical_component_id uuid,
    assessment_id uuid,
    primary key (id)
);
create table technical_components
(
    id uuid default random_uuid(),
    name varchar(1024),
    type varchar(64),
    uri varchar(2048),
    primary key (id)
);
create table technical_components_meta
(
    technical_component_id uuid,
    value varchar(1024),
    key varchar(2048),
    primary key (technical_component_id,key)
);