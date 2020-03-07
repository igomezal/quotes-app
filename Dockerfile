FROM oracle/graalvm-ce:19.3.1-java8 as graalvm
# For JDK 11
#FROM oracle/graalvm-ce:19.3.1-java11 as graalvm
RUN gu install native-image

COPY . /home/app/quotes-app
WORKDIR /home/app/quotes-app

RUN native-image --no-server -cp build/libs/quotes-app-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/quotes-app/quotes-app /app/quotes-app
ENTRYPOINT ["/app/quotes-app"]
