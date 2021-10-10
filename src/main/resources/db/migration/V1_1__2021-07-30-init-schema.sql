create table applications
(
    id uuid default gen_random_uuid(),
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
    id uuid default gen_random_uuid(),
    state varchar(128),
    time_start bigint,
    primary key (id)
);
create table components
(
    id uuid default gen_random_uuid(),
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
    id uuid default gen_random_uuid(),
    name varchar(1024),
    type varchar(1024),
    docker_command varchar(2048),
    primary key (id)
);
create table issues
(
    id uuid default gen_random_uuid(),
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
    id uuid default gen_random_uuid(),
    meta varchar(1024),
    state varchar(64),
    control_id uuid,
    technical_component_id uuid,
    assessment_id uuid,
    primary key (id)
);
create table technical_components
(
    id uuid default gen_random_uuid(),
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

create table datasets
(
    id uuid default gen_random_uuid(),
    name varchar(128),
    source varchar(1024),
    primary key (id)
);
create table dataset_categories
(
    dataset_id uuid,
    category varchar(128),
    primary key (dataset_id, category)
);
create table components_datasets
(
    component_id uuid,
    dataset_id uuid,
    primary key (component_id,dataset_id)
);
create table usecases
(
    id uuid default gen_random_uuid(),
    name varchar(128),
    actor varchar(128),
    action varchar(1024),
    application_id uuid,
    primary key (id)
);
create table usecases_components
(
    usecase_id uuid,
    component_id uuid,
    primary key (component_id,usecase_id)
);
create table architecture
(
    id uuid default gen_random_uuid(),
    application_id uuid,
    primary key (id)
);
create table architecture_links
(
    id uuid default gen_random_uuid(),
    architecture_id uuid,
    source_component_id uuid,
    destination_component_id uuid,
    primary key (id)
);