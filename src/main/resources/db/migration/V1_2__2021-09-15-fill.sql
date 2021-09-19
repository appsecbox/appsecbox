INSERT INTO controls (id, name, docker_command, type)
VALUES
       ( '9f0aaa77-f14b-4175-98c6-7ff71e34ad1f', 'TEST_CONTROL', 'docker run -v $(pwd):/path checkmarx/kics:latest scan -p "/path" -o "/path/"', 'SOURCE_CODE' );
