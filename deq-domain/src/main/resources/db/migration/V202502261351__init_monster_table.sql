CREATE SEQUENCE IF NOT EXISTS monster_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE monster
(
    external_id      BIGINT  NOT NULL,
    id               BIGINT  NOT NULL,
    name             VARCHAR(255),
    url              VARCHAR(255),
    avatar_url       VARCHAR(255),
    description      TEXT,
    size             SMALLINT,
    type             SMALLINT,
    armor_class      INTEGER NOT NULL,
    challenge_rating INTEGER NOT NULL,
    CONSTRAINT pk_monster PRIMARY KEY (id),
    constraint uq_monster_external_id unique (external_id)
);

CREATE TABLE monster_environments
(
    monster_id   BIGINT NOT NULL,
    environments SMALLINT
);

CREATE TABLE monster_languages
(
    monster_id BIGINT NOT NULL,
    languages  SMALLINT
);

CREATE TABLE monster_possible_alignments
(
    monster_id          BIGINT NOT NULL,
    possible_alignments SMALLINT
);

CREATE TABLE monster_stats
(
    monster_id BIGINT   NOT NULL,
    stats      BIGINT,
    stats_key  SMALLINT NOT NULL,
    CONSTRAINT pk_monster_stats PRIMARY KEY (monster_id, stats_key)
);

ALTER TABLE monster_environments
    ADD CONSTRAINT fk_monster_environments_on_monster FOREIGN KEY (monster_id) REFERENCES monster (id);

ALTER TABLE monster_languages
    ADD CONSTRAINT fk_monster_languages_on_monster FOREIGN KEY (monster_id) REFERENCES monster (id);

ALTER TABLE monster_possible_alignments
    ADD CONSTRAINT fk_monster_possiblealignments_on_monster FOREIGN KEY (monster_id) REFERENCES monster (id);

ALTER TABLE monster_stats
    ADD CONSTRAINT fk_monster_stats_on_monster FOREIGN KEY (monster_id) REFERENCES monster (id);