CREATE SEQUENCE IF NOT EXISTS scoreboard_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS public.scoreboard
(
    id         BIGINT NOT NULL DEFAULT nextval('scoreboard_seq'),
    code       varchar(255),
    created_at TIMESTAMP,
    CONSTRAINT pk_codes PRIMARY KEY (id)
);
ALTER SEQUENCE scoreboard_seq OWNED BY scoreboard.id;
INSERT INTO scoreboard(id, code, created_at)
VALUES (nextval('scoreboard_seq'), 'a0a0', now());
