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
INSERT INTO media (
        media_url,
        public_id,
        media_type,
        media_format,
        resource_type,
        project_id
    )
VALUES (
        'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521183/charitan/project/totrxi5roliazxgvby8k.jpg',
        'charitan/project/totrxi5roliazxgvby8k',
        'IMAGE',
        'jpg',
        'image',
        '9284aa44-9bea-4647-80bd-55c413590404',
    ),
    (
        'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521183/charitan/project/sbsnoac8rbwpuqep51w9.jpg',
        'charitan/project/sbsnoac8rbwpuqep51w9',
        'IMAGE',
        'jpg',
        'image',
        '9284aa44-9bea-4647-80bd-55c413590404',
    ),
    (
        'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/project/qmpmiinirmv9xxmgxtxm.jpg',
        'charitan/project/qmpmiinirmv9xxmgxtxm',
        'IMAGE',
        'jpg',
        'image',
        '9284aa44-9bea-4647-80bd-55c413590404',
    );