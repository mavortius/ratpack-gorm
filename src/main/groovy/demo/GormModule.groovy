package demo

import com.google.inject.AbstractModule
import com.google.inject.Provides
import org.grails.orm.hibernate.HibernateDatastore

class GormModule extends AbstractModule {
    @Override
    protected void configure() { }

    @Provides
    HibernateDatastore hibernateDatastore() {
        Map configuration = [
                'hibernate.hbm2ddl.auto':'create-drop',
                'dataSource.driverClassName':'org.h2.Driver',
                'dataSource.url':'jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE',
                'dataSource.username':'sa',
                'dataSource.password':'',
                'dataSource.dialect':'org.hibernate.dialect.H2Dialect'
        ]

        HibernateDatastore datastore = new HibernateDatastore(configuration, Person)

        datastore
    }
}
