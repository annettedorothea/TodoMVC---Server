# TodoMVC---Server

This is the server for the acegen TodoMVC sample.

See [TodoMVC](https://todo.acegen.de/#).

It is written with Java, is based on the [de.acegen](https://github.com/annettedorothea/de.acegen) 
DSL and code generator and uses Dropwizard as http server framework.

See [TodoMVC---Client](https://github.com/annettedorothea/TodoMVC---Client) for the client project.

## Installation

- Get latest Ecplise.
- Install the [de.acegen](https://github.com/annettedorothea/de.acegen) Ecplise plugin from [http://acegen.de](http://acegen.de).
- Maven install the project dependencies.
- Create a PostgreSQL database.
- Create a yml file like [sample.yml](sample.yml).
- Run the migrations with `db migrate <your-yml-file>.yml` as argument.
- Start the application with the arguments `server <your-yml-file>.yml` as argument.

## Execute the tests

If you want to execute the tests you start the server in dev mode:

```
config:
  mode: DEV
  writeTimeline: ALWAYS
```

Please note that the database is cleared on startup of the server.

When the test-server is running you can execute the tests.

## License
[ISC](License.txt)
