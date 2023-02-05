package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getlistUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByModelAndSeries(String model, int series) {
        String hql = "FROM Car car INNER JOIN FETCH car.user Where car.model=:carMod AND car.series=:carSer";
        Query<Car> query = sessionFactory.getCurrentSession().createQuery(hql, Car.class);
        query.setParameter("carMod", model);
        query.setParameter("carSer", series);
        Car car = query.uniqueResult();
        User user = car.getUser();
        return user;
    }

}
