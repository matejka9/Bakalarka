package com.example.dusky.myapplication.backend.manager;

import com.example.dusky.myapplication.backend.model.database.BaseDataEntityModel;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import org.apache.commons.beanutils.BeanUtils;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by dusky on 5/18/16.
 */
public class ResultSetMapper<T> {

    private static Logger logger = Logger.getLogger(ResultSetMapper.class.getName());

    protected Class outputClass;
    protected Set<Field> fieldSet = null;

    public ResultSetMapper(Class outputClass) {
        this.outputClass = outputClass;
        this.fieldSet = new HashSet<Field>();
        Class<?> current = outputClass;
        while (current != BaseDataEntityModel.class) {
            if (current.getDeclaredFields() != null) {
                this.fieldSet.addAll(new HashSet(Arrays.asList(current.getDeclaredFields())));
            }
            current = current.getSuperclass();
        }
        if (outputClass.getDeclaredFields() != null) {
            this.fieldSet.addAll(new HashSet(Arrays.asList(outputClass.getDeclaredFields())));
        }
        this.fieldSet = Sets.filter(this.fieldSet, new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field field) {
                return field.isAnnotationPresent(Column.class);
            }
        });
    }

    public T mapResultSetToObject(ResultSet rs) {
        //logger.log(Level.INFO, outputClass + ", " + rs.toString() + ", " + rs.getMetaData().toString());
        T output = null;
        try {
            //logger.log(Level.INFO, outputClass + ", " + rs.toString() + ", " + rs.getMetaData().toString());
            // make sure resultset is not null
            if (rs != null) {
                // check if outputClass has 'Entity' annotation
                if (this.outputClass.isAnnotationPresent(Entity.class)) {
                    // get the resultset metadata
                    ResultSetMetaData rsmd = rs.getMetaData();
                    // get all the attributes of outputClass
//                    logger.log(Level.INFO, rsmd.toString());
                    if (this.fieldSet != null && this.fieldSet.size() > 0) {
//                        logger.log(Level.INFO, outputClass.toString() + " fields: " + fieldSet.toString());
                        output = (T) this.outputClass.newInstance();
                        for (int _iterator = 1; _iterator <= rsmd.getColumnCount(); _iterator++) {
                            // getting the SQL column name
                            //String columnName = rsmd.getColumnName(_iterator);
                            String columnName = rsmd.getColumnLabel(_iterator);
                            // reading the value of the SQL column
                            Object columnValue = rs.getObject(_iterator);

                            //logger.log(Level.INFO, rsmd.getColumnName(_iterator) + "[" + rsmd.getColumnLabel(_iterator) + "]: " + rs.getObject(_iterator));

                            //logger.log(Level.INFO, columnName + ": " + columnValue);
                            // iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
                            for (Field field : this.fieldSet) {
//                                logger.log(Level.INFO, field.getAnnotation(Column.class).toString());
                                if (field.getAnnotation(Column.class).name().equalsIgnoreCase(columnName) && columnValue != null) {
                                    //logger.log(Level.INFO, "SET [" + columnName + "]: " + columnValue);
                                    BeanUtils.setProperty(output, field.getName(), columnValue);
                                    break;
                                }
                            }
                        }
//                            logger.log(Level.INFO, "<" + outputClass.toString() + ">: " + output);
                    }
                    else {
                        logger.log(Level.FINE, this.outputClass.toString() + " hasn't any valid fields");
                        logger.log(Level.INFO, this.outputClass.toString() + " hasn't any valid fields");
                    }
                }
                else {
                    // throw some error
                    logger.log(Level.FINE, this.outputClass.toString() + " hasn't " + Entity.class.toString() + " annotation");
                    logger.log(Level.INFO, this.outputClass.toString() + " hasn't " + Entity.class.toString() + " annotation");
                }
            }
            else {
                logger.log(Level.FINE, "result set for " + this.outputClass.toString() + " is null");
                logger.log(Level.INFO, "result set for " + this.outputClass.toString() + " is null");
            }
        }
        catch (IllegalAccessException e) {
            logger.log(Level.FINEST, e.toString());
            logger.log(Level.INFO, e.toString());
//            e.printStackTrace();
        }
        catch (SQLException e) {
            logger.log(Level.FINEST, e.toString());
            logger.log(Level.INFO, e.toString());
//            e.printStackTrace();
        }
        catch (InstantiationException e) {
            logger.log(Level.FINEST, e.toString());
            logger.log(Level.INFO, e.toString());
//            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
//            e.printStackTrace();
            logger.log(Level.FINEST, e.toString());
            logger.log(Level.INFO, e.toString());
        }
        return output;
    }

}