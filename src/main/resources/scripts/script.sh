#!/bin/sh

GIT_REPOSITORY="https://github.com/ReactiveX/RxJava.git"
WORKING_DIRECTORY="/Users/emreyilmaz/Desktop/master/project/test"

#declare a global array to store tags
declare -a tags

#get all tags of a remote repository
getTags () {
  response=( $(git ls-remote --tags "$GIT_REPOSITORY") )
  removedParts="refs/tags/"
  for item in "${response[@]}"
  do
    :
    if [[ $item == *"refs/tags/"* ]]; then
      tags=("${item//$removedParts/}" "${tags[@]}")
    fi
  done
}


#clone all tags of a remote repository
cloneTags () {
  for tag in "${tags[@]}"
  do
    :
    mkdir "$tag"
    cd "$tag"

    git clone -b "$tags" "$GIT_REPOSITORY"

    # shellcheck disable=SC2103
    cd ".."
  done
}

#build all tags repository
buildProjects () {
  for tag in "${tags[@]}"
  do
    :
    mkdir "$tag"
    cd "$tag"

    mvn install

    # shellcheck disable=SC2103
    cd ".."
  done
}

cd $WORKING_DIRECTORY

getTags

cloneTags

buildTags

