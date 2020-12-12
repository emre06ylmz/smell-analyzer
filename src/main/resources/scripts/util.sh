#!/bin/sh

GIT_REPOSITORY="https://github.com/ReactiveX/RxJava.git"
response=( $(git ls-remote --tags "$GIT_REPOSITORY") )

  removedParts="refs/tags/"
  for item in "${response[@]}"
  do
    :
    if [[ $item == *"refs/tags/"* ]]; then
      tags=("${item//$removedParts/}" "${tags[@]}")
    fi
  done

echo ${tags[*]}