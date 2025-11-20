# README

The is the Feedback API completed with Spring.

## Docker Compose (11/17)

This will use Docker Compose for the following:

- PostgreSQL
- Kafka (the Broker)
- Kafka Tools
- Schema Registry
- Control Center

### Confluent: CP-Server

1. Pull the Image: `docker pull confluentinc/cp-server:latest-ubi8`
2. 

## PostgreSQL (through Docker)

### Settings

- Username: See *.env* file
- Password: See *.env* file
- Host:
  - **IF** accessing from local machine (outside container): `localhost`
  - **IF** accessing from another *Container* (inside Docker network): Service Name of PostgreSQL Container
- Database Name: `final_project`
- Port: `5433` | `5432` (Local | Docker)

### Manual Docker Steps

1. Start PostgreSQL instance:

```
docker run \
--name cont-postgres \
-p 5433:5432 \
-e POSTGRES_PASSWORD=FinalProject123 \
-e POSTGRES_DB=final_project \
-d postgres
```

2. Enter Shell for PostgreSQL container: `docker exec -it cont-postgres bash`
3. Enter *psql* Shell: `psql -U postgres -d final_project` (password is not required)

**Note:**
- `docker run` command:
  - `--name cont-postgres` - provide the *Container* name
  - `-p 5433:5432` - set Local / Docker ports
  - `-e POSTGRES_PASSWORD=FinalProject123` - set environment variables
  - `-e POSTGRES_DB=final_project` - define default database to create/start into
  - `-d` - run *Container* in "detached" mode

### Dockerfile
TBD...

### Docker Compose

[See Docker Hub for Instructions](https://hub.docker.com/_/postgres)

## Potential Future Add-Ons
The following is a list of *potential future add-ons* and *best-practice* alternatives not used in this code-base:

- `FeedbackService` - `createNewFeedbackEntry()`: Logic to prevent multiple review
  - More of a nice-to-have, however it is out of scope/spec