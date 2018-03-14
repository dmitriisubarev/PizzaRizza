package ru.efirus.app.pizzarizza;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by efirus on 12.03.18.
 */
@Dao
public interface BasketDao {
    @Query("SELECT * FROM basket")
    List<Basket> getAll();

    @Query("SELECT * FROM basket WHERE basketid IN (:basketIds)")
    List<Basket> loadAllByIds(int[] basketIds);

    @Query("SELECT * FROM basket WHERE product_name LIKE :product LIMIT 1")
    Basket findByName(String product);
    /*@Query("SELECT * FROM basket WHERE product_name LIKE :product AND "
            + "product_price LIKE :price AND "
            + "product_quent LIKE :quent LIMIT 1")
    Basket findByName(String product, int price, int quent);*/

    @Insert
    void insert(Basket basket);

    @Update
    void update(Basket basket);

    @Delete
    void delete(Basket basket);

    @Delete
    void deleteAll(List<Basket> baskets);



}
