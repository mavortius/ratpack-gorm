import demo.GormModule
import demo.Person
import org.grails.orm.hibernate.HibernateDatastore
import ratpack.exec.Blocking
import ratpack.service.Service
import ratpack.service.StartEvent

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    bindings {
        module GormModule

        bindInstance new Service() {
            @Override
            void onStart(StartEvent event) throws Exception {
                event.getRegistry().get(HibernateDatastore)

                Blocking.exec {
                    Person.withNewSession {
                        if(!Person.count()) {
                            new Person(firstName: 'Marcelo', lastName: 'Martins').save(flush: true)
                            new Person(firstName: 'Magaly', lastName: 'Silva').save(flush: true)
                            new Person(firstName: 'Edna', lastName: 'Brito').save(flush: true)
                        }
                    }
                }
            }
        }
    }

    handlers {
        get {
            Blocking.get {
                Person.withNewSession {
                    Person.list()
                }
            } then { names ->
                render(json(names))
            }
        }
    }
}
