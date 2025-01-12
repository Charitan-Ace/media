DROP TABLE IF EXISTS media CASCADE;
CREATE TABLE media (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    media_url VARCHAR(255),
    public_id VARCHAR(255),
    media_type VARCHAR(50),
    media_format VARCHAR(50),
    resource_type VARCHAR(50),
    is_thumbnail BOOLEAN,
    project_id VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-------------------------- IMAGE
---- Supporting SOS Children’s Village China
INSERT INTO media (
        media_url,
        public_id,
        media_type,
        media_format,
        resource_type,
        is_thumbnail,
        project_id
    )
VALUES (
        'https://res.cloudinary.com/doorb2fhk/image/upload/v1736663337/charitan/image/project/ocqcevlkb2fp0bfnae74.jpg',
        'charitan/image/project/ocqcevlkb2fp0bfnae74',
        'IMAGE',
        'jpg',
        'image',
        FALSE,
        '9284aa44-9bea-4647-80bd-55c413590404'
    ),
    (
        'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521183/charitan/image/project/ur91jwclwgjlyliev6pz',
        'charitan/image/project/ur91jwclwgjlyliev6pz',
        'IMAGE',
        'jpg',
        'image',
        FALSE,
        '9284aa44-9bea-4647-80bd-55c413590404'
    ),
    (
        'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/xzqayqvcq3w5r4xamkdj',
        'charitan/image/project/xzqayqvcq3w5r4xamkdj',
        'IMAGE',
        'jpg',
        'image',
        FALSE,
        '9284aa44-9bea-4647-80bd-55c413590404'
    ),
    (
        'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/th61lyubnpxnpcyqg4lw',
        'charitan/image/project/th61lyubnpxnpcyqg4lw',
        'IMAGE',
        'jpg',
        'image',
        FALSE,
        '9284aa44-9bea-4647-80bd-55c413590404'
    ),
    (
        'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/san8oc1ugzdr1l4csv0z',
        'charitan/image/project/san8oc1ugzdr1l4csv0z',
        'IMAGE',
        'jpg',
        'image',
        FALSE,
        '9284aa44-9bea-4647-80bd-55c413590404'
    );
--- Helping Ukrainian Refugee DE
-- INSERT INTO media (
--         media_url,
--         public_id,
--         media_type,
--         media_format,
--         resource_type,
--         project_id
--     )
-- VALUES (
--         'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/mnaojsvqgtylhug36de4',
--         'charitan/image/project/mnaojsvqgtylhug36de4',
--         'IMAGE',
--         'jpg',
--         'image',
--         '144a5377-2ccc-4efe-81e8-18a6da36450b'
--     ),
--     (
--         'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/yvcisnnaxgv2imdtjc3a',
--         'charitan/image/project/yvcisnnaxgv2imdtjc3a',
--         'IMAGE',
--         'jpg',
--         'image',
--         '144a5377-2ccc-4efe-81e8-18a6da36450b'
--     ),
--     (
--         'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/xtol47ao2kucylaa98wy',
--         'charitan/image/project/xtol47ao2kucylaa98wy',
--         'IMAGE',
--         'jpg',
--         'image',
--         '144a5377-2ccc-4efe-81e8-18a6da36450b'
--     ),
--     (
--         'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/f7v5r82nivjgwcmem6xk',
--         'charitan/image/project/f7v5r82nivjgwcmem6xk',
--         'IMAGE',
--         'jpg',
--         'image',
--         '144a5377-2ccc-4efe-81e8-18a6da36450b'
--     ),
--     (
--         'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/m8xwyzxpl9hsbfj67jht',
--         'charitan/image/project/m8xwyzxpl9hsbfj67jht',
--         'IMAGE',
--         'jpg',
--         'image',
--         '144a5377-2ccc-4efe-81e8-18a6da36450b'
--     );
-- --- Helping Ukrainian Refugee DE
-- INSERT INTO media (
--         media_url,
--         public_id,
--         media_type,
--         media_format,
--         resource_type,
--         project_id
--     )
-- VALUES (
--         'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/mnaojsvqgtylhug36de4',
--         'charitan/image/project/mnaojsvqgtylhug36de4',
--         'IMAGE',
--         'jpg',
--         'image',
--         '144a5377-2ccc-4efe-81e8-18a6da36450b'
--     ),
--     (
--         'https://res.cloudinary.com/doorb2fhk/image/upload/v1736521181/charitan/image/project/yvcisnnaxgv2imdtjc3a',
--         'charitan/image/project/yvcisnnaxgv2imdtjc3a',
--         'IMAGE',
--         'jpg',
--         'image',
--         '144a5377-2ccc-4efe-81e8-18a6da36450b'
--     ),