#!/usr/bin/env bash
set -euo pipefail

fail() {
    echo "$1"
    exit 1
}

validate_project_reports_path() {
    echo "Validating project reports path..."
    export WORKING_DIR=`cd $(dirname "$0") && pwd`
    export PROJECT_REPORTS_PATH=${WORKING_DIR}/reports/test-results
    echo "PROJECT_REPORTS_PATH is ${PROJECT_REPORTS_PATH}"
}

validate_github_owner_repo() {
    echo "Validating github owner and repo..."
    if [ ! -z "${2}" ] || [ ! -z "${3}" ]; then
        echo "GH_OWNER AND/OR GH_REPO not present on command line. Using default"
        export GH_OWNER=qcif
        export GH_REPO=acceptance-testing
    else
        export GH_OWNER=${2}
        export GH_REPO=${3}
    fi
    export GH_OWNER_REPO="${GH_OWNER}/${GH_REPO}"
    echo "Updating github repo: ${GH_OWNER_REPO}"
}

init_gh_pages_repo() {
    echo "cloning acceptance testing report repo..."
    cd /tmp
    git clone https://github.com/${GH_OWNER_REPO}
    cd ${GH_REPO}
    git checkout -B gh-pages
}

update_gh_pages_repo() {
    echo "removing current pages...."
    rm -Rf *
    ls -lhrt .
    echo "copying PROJECT REPORT DIR: ${PROJECT_REPORTS_PATH}"
    cp -Rf ${PROJECT_REPORTS_PATH} ./
    git add --all .
    git commit -m "updating reports for ${GH_OWNER_REPO}..."
    git push origin gh-pages
}

validate_project_reports_path
validate_github_owner_repo
init_gh_pages_repo
update_gh_pages_repo

echo "push to gh-pages succeeded."
echo "Reports available at https://${GH_OWNER}.github.io/${GH_REPO}/"