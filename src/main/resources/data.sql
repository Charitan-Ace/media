DROP TABLE IF EXISTS media CASCADE;
CREATE TABLE media (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    media_url VARCHAR(255),
    public_id VARCHAR(255),
    media_type VARCHAR(50),
    media_format VARCHAR(50),
    resource_type VARCHAR(50),
    project_id VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- For images
INSERT INTO media
(
    media_url,
    public_id,
    media_type,
    media_format,
    resource_type,
    project_id
)
VALUES
(
    



)

