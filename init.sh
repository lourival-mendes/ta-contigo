#!/bin/bash
mongosh --host localhost --port 27017 -u admin -p secret --authenticationDatabase admin <<EOF
use admin
db.createUser({
    user: "admin",
    pwd: "secret",
    roles: [{ role: "root", db: "admin" }]
});
EOF