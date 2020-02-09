FROM skymindops/zeppelin-dl4j:latest
VOLUME /tmp
COPY target/StockPrediction.jar StockPrediction.jar
RUN bash -c "touch /StockPrediction.jar"
EXPOSE 18060
ENTRYPOINT ["java","-jar","StockPrediction.jar"]
