CREATE TABLE IF NOT EXISTS client (
    id                  BIGSERIAL   NOT NULL,
    created_at          TIMESTAMP   NOT NULL DEFAULT NOW(),
    created_by          VARCHAR     NOT NULL,
    last_modified_at    TIMESTAMP,
    last_modified_by    VARCHAR,
    description         VARCHAR(60) NOT NULL UNIQUE,
	active              BOOLEAN     NOT NULL,
    CONSTRAINT          client_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS market (
    id                  BIGSERIAL   NOT NULL,
    created_at          TIMESTAMP   NOT NULL DEFAULT NOW(),
    created_by          VARCHAR     NOT NULL,
    last_modified_at    TIMESTAMP,
    last_modified_by    VARCHAR,
    code                VARCHAR(10) NOT NULL UNIQUE,
    description         VARCHAR(60) NOT NULL,
    country             VARCHAR(2)  NOT NULL,
	active              BOOLEAN     NOT NULL,
    CONSTRAINT          market_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS client_market (
    client_id           BIGINT NOT NULL,
    market_id           BIGINT NOT NULL,
    CONSTRAINT          client_market_pkey PRIMARY KEY (client_id,market_id),
    CONSTRAINT client_market_client_fk FOREIGN KEY(client_id) REFERENCES client(id) ON DELETE CASCADE,
    CONSTRAINT client_market_market_fk FOREIGN KEY(market_id) REFERENCES market(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_client_market_client_id ON client_market(client_id);
CREATE INDEX IF NOT EXISTS idx_client_market_market_id ON client_market(market_id);

