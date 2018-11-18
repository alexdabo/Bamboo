mvn -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=webapp-javaee7 -DarchetypeVersion=1.1 -DarchetypeRepository=https://repo.maven.apache.org/maven2 -DgroupId=com.bamboo -DartifactId=Bamboo -Dversion=1.0-SNAPSHOT -Dpackage=com.bamboo -Dbasedir=~/Projects/java -Darchetype.interactive=false --batch-mode archetype:generate

mvn -Dtest=com.bamboo.test.unit.AnotherServiceTest surefire:test

