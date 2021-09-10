run: compile
	java --module-path JavaFXForLinux/ --add-modules javafx.controls,javafx.web FlightFinder

compile: FlightFinder.java FrontEnd.java FileReader.java BackEnd.java CustomEdge.java ffStyle.css GraphADT.java ShortestPathToString.java
	javac --module-path JavaFXForLinux/ --add-modules javafx.controls,javafx.web FlightFinder.java

test:
	javac -cp .:junit5.jar TestClass2.java
	java -jar junit5.jar -cp . --scan-classpath

clean: 
	$(RM) *.class
