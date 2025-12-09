#!/usr/bin/env bash

# Builds all applications and all docker images using Dockerfile and tags it based on org.opencontainers.image.version
# label in Dockerfile.

#######################################
# Script main function. Builds all applications and all docker images using Dockerfile and tags it based on
# org.opencontainers.image.version label in Dockerfile.
# Arguments:
#   None.
#######################################
function main() {
    cd ./item-service/; bash ./build.sh; cd ..
    cd ./category-service/; bash ./build.sh; cd ..
    cd ./gateway/; bash ./build.sh; cd ..
    cd ./ui/; bash ./build.sh; cd ..
}

main "$@"
