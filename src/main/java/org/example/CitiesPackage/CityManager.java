package org.example.CitiesPackage;

import org.example.Basic_comm.Registration;
import org.example.Examination.IsDouble;
import org.example.Examination.IsFloat;
import org.example.Examination.IsInt;
import org.example.Examination.IsLong;
import org.w3c.dom.*;

import java.sql.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CityManager {
    private static ArrayList<City> cityCollection = new ArrayList<>();


//    public void loadCollectionFromFile()  {
//        try {
//            // Загружаем XML файл в память в виде DOM дерева
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File("city_collection.xml"));
//
//            // Получаем корневой элемент "cities"
//            Element rootElement = document.getDocumentElement();
//
//            // Получаем список всех элементов "city"
//            NodeList cityNodes = rootElement.getElementsByTagName("city");
//
//            // Создаем пустой список для хранения объектов City
//
//            // Перебираем все элементы "city"
//            for (int i = 0; i < cityNodes.getLength(); i++) {
//                boolean flag = true;
//                Element cityElement = (Element) cityNodes.item(i);
//
//                // Получаем значения полей элемента "city"
//                String name = cityElement.getElementsByTagName("name").item(0).getTextContent();
//                if (name.equals("") ) {
//                    System.out.println("В названии города допущена ошибка. Посмотрите загрузочный файл. Name: " + i);
//                    flag = false;
//                }
//
//                String id = cityElement.getElementsByTagName("id").item(0).getTextContent();
//                if (!IsInt.isInt(id)) {
//                    System.out.println("Неверный id. Значение должно быть больше 0. Посмотрите загрузочный файл. Name: " + i);
//                    flag = false;
//
//                }
//                else {
//                    if (Integer.parseInt(id) <= 0 || id.equals("")) {
//                        System.out.println("Неверный id. Значение должно быть больше 0. Посмотрите загрузочный файл. Name: " + i);
//                        flag = false;
//                    }
//                }
//                String telephoneCode = cityElement.getElementsByTagName("telephoneCode").item(0).getTextContent();
//                if (!IsLong.isLong(telephoneCode)) {
//                    System.out.println("Неверный telephoneCode. Значение должно быть больше 0 и меньше 100000. Посмотрите загрузочный файл. Name: " + i);
//                    flag = false;
//                }
//                else {
//                    if ((Long.parseLong(telephoneCode) <= 0) || (Long.parseLong(telephoneCode) > 100000) || (telephoneCode).equals("")) {
//                        System.out.println("Неверный telephoneCode. Значение должно быть больше 0 и меньше 100000. Посмотрите загрузочный файл. Name: " + i);
//                        flag = false;
//                    }
//                }
//
//                String carcode = cityElement.getElementsByTagName("carcode").item(0).getTextContent();
//                if (!IsLong.isLong(carcode)) {
//                    System.out.println("Неверный carcode. Значение должно быть больше 0 и меньше 100000, и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
//                    flag = false;
//                }
//                else {
//                    if ((Long.parseLong(carcode) <= 0) || (Long.parseLong(carcode) > 100000) || (carcode).equals("")) {
//                        System.out.println("Неверный carcode. Значение должно быть больше 0 и меньше 100000, и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
//                        flag = false;
//                    }
//                }
//
//                String population = cityElement.getElementsByTagName("population").item(0).getTextContent();
//                if (!IsLong.isLong(population)) {
//                    System.out.println("Неверный population. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
//                    flag = false;
//                }
//                else {
//                    if ((Long.parseLong(population) <= 0) || (population.equals(""))) {
//                        System.out.println("Неверный population. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
//                        flag = false;
//                    }
//                }
//
//                String  area = cityElement.getElementsByTagName("area").item(0).getTextContent();
//                if (!IsFloat.isFloat(area)) {
//                    System.out.println("Неверный area. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
//                    flag = false;
//                }
//                else {
//                    if ((Float.parseFloat(area) <= 0) || (area.equals(""))) {
//                        System.out.println("Неверный area. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
//                        flag = false;
//                    }
//                }
//
//                String metersAboveSeaLevel = cityElement.getElementsByTagName("metersAboveSeaLevel").item(0).getTextContent();
//                if (!IsDouble.isDouble(metersAboveSeaLevel)) {
//                    flag = false;
//                    System.out.println("Ошибка в metersAboveSeaLevel. Name: " + i);
//                }
//
//                String age = cityElement.getElementsByTagName("age").item(0).getTextContent();
//                if (!IsInt.isInt(age)) {
//                    System.out.println("Неверный age. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
//                    flag = false;
//                }
//
//                else {
//                    if ((Integer.parseInt(age) <= 0) || (age.equals(""))) {
//                        System.out.println("Неверный age. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
//                        flag = false;
//
//                    }
//                }
//                String standardOfLiving = cityElement.getElementsByTagName("standardOfLiving").item(0).getTextContent();
//                if (!standardOfLiving.equals("ULTRA_HIGH") & !standardOfLiving.equals("HIGH") & !standardOfLiving.equals("MEDIUM") & !standardOfLiving.equals("ULTRA_LOW") & !standardOfLiving.equals("NIGHTMARE")) {
//                    System.out.println("Неверный standardOfLiving. Значение должно соответствовать значениям класса enum. Посмотрите загрузочный файл. Name: " + i);
//                    flag = false;
//                }
//                if (flag) {
//                    String creationDate = cityElement.getElementsByTagName("creationDate").item(0).getTextContent();
//                    Human governor = new Human();
//                    governor.setAge(Integer.parseInt(age));
//                    // Создаем объект City на основе считанных данных
//                    City city = new City();
//                    city.setName(name);
//                    city.setId(Integer.parseInt(id));
//                    city.setTelephoneCode(Long.parseLong(telephoneCode));
//                    city.setCarCode(Long.parseLong(carcode));
//                    city.setPopulation(Long.parseLong(population));
//                    city.setArea(Float.parseFloat(area));
//                    city.setMetersAboveSeaLevel(Double.parseDouble(metersAboveSeaLevel));
//                    city.setGovernor(governor);
//                    city.setStandardOfLiving(StandardOfLiving.valueOf(standardOfLiving));
//                    city.setCreationDate(creationDate);
//                    cityCollection.add(city);
//                }
//            }
//            System.out.println(cityCollection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void loadCollectionFromDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CityApp", "postgres", "online");

            String sql = "SELECT * FROM cities";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                boolean flag = true;

                int id = rs.getInt("id");
                if (!IsInt.isInt(String.valueOf(id)) || id <= 0) {
                    System.out.println("Неверный id. Значение должно быть больше 0. Name: " + rs.getString("name"));
                    flag = false;
                }

                String name = rs.getString("name");
                if (name.equals("") || name.equals(" ")) {
                    System.out.println("В названии города допущена ошибка. Посмотрите загрузочный файл. Name: " + rs.getString("name"));
                    flag = false;
                }

                String creationDate = rs.getString("creation_date");

                float area = rs.getFloat("area");
                if (!IsFloat.isFloat(String.valueOf(area)) || area <= 0) {
                    System.out.println("Неверный area. Значение должно быть больше 0 и не может быть пустым. Name: " + rs.getString("name"));
                    flag = false;
                }

                long population = rs.getLong("population");
                if (!IsLong.isLong(String.valueOf(population)) || population <= 0) {
                    System.out.println("Неверный population. Значение должно быть больше 0 и не может быть пустым. Name: " + rs.getString("name"));
                    flag = false;
                }

                double metersAboveSeaLevel = rs.getDouble("meters_above_sea_level");
                if (!IsDouble.isDouble(String.valueOf(metersAboveSeaLevel))) {
                    System.out.println("Ошибка в metersAboveSeaLevel. Name: " + rs.getString("name"));
                    flag = false;
                }

                long telephoneCode = rs.getLong("telephone_code");
                if (!IsLong.isLong(String.valueOf(telephoneCode)) || telephoneCode <= 0 || telephoneCode > 100000) {
                    System.out.println("Неверный telephoneCode. Значение должно быть больше 0 и меньше 100000. Name: " + rs.getString("name"));
                    flag = false;
                }

                long carCode = rs.getLong("car_code");
                if (!IsLong.isLong(String.valueOf(carCode)) || carCode <= 0 || carCode > 100000) {
                    System.out.println("Неверный carCode. Значение должно быть больше 0 и меньше 100000, и не может быть пустым. Name: " + rs.getString("name"));
                    flag = false;
                }

                int governorAge = rs.getInt("governor_age");
                if (!IsInt.isInt(String.valueOf(governorAge)) || governorAge <= 0) {
                    System.out.println("Неверный age. Значение должно быть больше 0 и не может быть пустым. Name: " + rs.getString("name"));
                    flag = false;
                }

                String standardOfLivingStr = rs.getString("standard_of_living");
                if (!standardOfLivingStr.equals("ULTRA_HIGH") && !standardOfLivingStr.equals("HIGH") && !standardOfLivingStr.equals("MEDIUM") && !standardOfLivingStr.equals("ULTRA_LOW") && !standardOfLivingStr.equals("NIGHTMARE")) {
                    System.out.println("Неверный standardOfLiving. Значение должно соответствовать значениям класса enum. Name: " + rs.getString("name"));
                    flag = false;
                }

                int clientId = rs.getInt("client_id");

                if (flag) {
                    StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(standardOfLivingStr);

                    Human governor = new Human();
                    governor.setAge(governorAge);

                    // Создаем объект City на основе считанных данных
                    City city = new City();
                    city.setId(id);
                    city.setName(name);
                    city.setCreationDate(creationDate);
                    city.setArea(area);
                    city.setPopulation(population);
                    city.setMetersAboveSeaLevel(metersAboveSeaLevel);
                    city.setTelephoneCode(telephoneCode);
                    city.setCarCode(carCode);
                    city.setStandardOfLiving(standardOfLiving);
                    city.setGovernor(governor);

                    cityCollection.add(city);
                }
            }

            rs.close();
            stmt.close();
            conn.close();

            System.out.println(cityCollection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


//    public void saveCollection() {
//        try {
//            // Создаем новый документ XML
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.newDocument();
//
//            // Создаем корневой элемент "cities"
//            Element rootElement = document.createElement("cities");
//            document.appendChild(rootElement);
//
//            // Для каждого города в коллекции создаем элемент "city" и добавляем его в корневой элемент
//            for (City city : cityCollection) {
//                Element cityElement = document.createElement("city");
//                rootElement.appendChild(cityElement);
//
//                // Добавляем дочерние элементы для каждого поля города
//                addElement(document, cityElement, "name", city.getName());
//                addElement(document, cityElement, "id", String.valueOf(city.getId()));
//                addElement(document, cityElement, "telephoneCode", String.valueOf(city.getTelephoneCode()));
//                addElement(document, cityElement, "standardOfLiving", String.valueOf(city.getStandardOfLiving()));
//                addElement(document, cityElement, "carcode", String.valueOf(city.getCarCode()));
//                addElement(document, cityElement, "population", String.valueOf(city.getPopulation()));
//                addElement(document, cityElement, "area", String.valueOf(city.getArea()));
//                addElement(document, cityElement, "metersAboveSeaLevel", String.valueOf(city.getMetersAboveSeaLevel()));
//                addElement(document, cityElement, "age", String.valueOf(city.getGovernor().getAge()));
//                addElement(document, cityElement, "creationDate", String.valueOf(city.getCreationDate()));
//            }
//
//            // Сохраняем измененное DOM дерево обратно в XML файл
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//            DOMSource source = new DOMSource(document);
//            StreamResult result = new StreamResult(new File("city_collection.xml"));
//            transformer.transform(source, result);
//
//            System.out.println("XML файл успешно сохранен.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
public void saveCollection() {
    try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CityApp", "postgres", "online");

        String sql = "INSERT INTO cities (name, id, telephone_code, standard_of_living, car_code, population, area, meters_above_sea_level, governor_age, creation_date, client_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (City city : cityCollection) {
            pstmt.setString(1, city.getName());
            pstmt.setInt(2, city.getId());
            pstmt.setLong(3, city.getTelephoneCode());
            pstmt.setString(4, String.valueOf(city.getStandardOfLiving()));
            pstmt.setLong(5, city.getCarCode());
            pstmt.setLong(6, city.getPopulation());
            pstmt.setFloat(7, city.getArea());
            pstmt.setDouble(8, city.getMetersAboveSeaLevel());
            pstmt.setInt(9, city.getGovernor().getAge());
            pstmt.setString(10, city.getCreationDate());
            pstmt.setInt(11, new Registration().getId());
            pstmt.addBatch();
        }

        pstmt.executeBatch();

        pstmt.close();
        conn.close();

        System.out.println("Данные коллекции успешно сохранены в базе данных.");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
}



    // Вспомогательный метод для добавления элемента в DOM дерево
    private void addElement(Document document, Element parentElement, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        parentElement.appendChild(element);
    }

    public  static ArrayList<City> getCollection(){
        return cityCollection;
    }
}




