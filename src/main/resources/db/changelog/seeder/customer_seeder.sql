INSERT INTO customer (email, password, role)
VALUES ('user@example.com', '{bcrypt}$2a$12$reL3cLpoDr9M89dmDm/Hvesl4lfJG7Uh2cs1ku7j2MpXvxS3mC/aW', 'read');

INSERT INTO customer (email, password, role)
VALUES ('admin@example.com', '{bcrypt}$2a$12$nvnn52mychOT74/1naV1Iu.lVjz0JraUZDKyKmGrDwZJYwVdmKI/K', 'admin');
