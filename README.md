# TodoMVC---Server

This is the server for the acegen TodoMVC sample.

See [TodoMVC](https://todo.acegen.de/#).

It is written with Java, is based on the [de.acegen](https://github.com/annettedorothea/de.acegen) 
DSL and code generator and uses Dropwizard as http server framework.

See [TodoMVC---Client](https://github.com/annettedorothea/TodoMVC---Client) for the client project.

## Setup

- You need Java
- Get [Maven](https://maven.apache.org/install.html)
- Go to root of the project where the [pom.xml](./pom.xml) is
- Run <code>mvn package -Dmaven.test.skip=true</code>
- Run <code>java -jar target/todo-&lt;current version&gt;.jar server dev.yml</code>

## Run the integration tests

- Start the server
- un <code>mvn test</code>

## View with DSL

- Get latest Ecplise
- Install the [de.acegen](https://github.com/annettedorothea/de.acegen) Ecplise plugin from [http://acegen.de](http://acegen.de)
- Create a project by choosing File - Import - Projects from Folder or Archive with the project root
- Open [todo-server.ace](./todo-server.ace)


## License
[ISC](License.txt)
