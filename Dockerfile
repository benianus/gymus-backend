FROM ubuntu:latest
LABEL authors="benianus"

ENTRYPOINT ["top", "-b"]