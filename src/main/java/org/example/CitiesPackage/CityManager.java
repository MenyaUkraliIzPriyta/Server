package org.example.CitiesPackage;

import org.example.Examination.IsDouble;
import org.example.Examination.IsFloat;
import org.example.Examination.IsInt;
import org.example.Examination.IsLong;
import org.w3c.dom.*;

import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CityManager {
    private static ArrayList<City> cityCollection = new ArrayList<>();


    public void loadCollectionFromFile()  {
        try {
            // Загружаем XML файл в память в виде DOM дерева
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("city_collection.xml"));

            // Получаем корневой элемент "cities"
            Element rootElement = document.getDocumentElement();

            // Получаем список всех элементов "city"
            NodeList cityNodes = rootElement.getElementsByTagName("city");

            // Создаем пустой список для хранения объектов City

            // Перебираем все элементы "city"
            for (int i = 0; i < cityNodes.getLength(); i++) {
                boolean flag = true;
                Element cityElement = (Element) cityNodes.item(i);

                // Получаем значения полей элемента "city"
                String name = cityElement.getElementsByTagName("name").item(0).getTextContent();
                if (name.equals("") ) {
                    System.out.println("В названии города допущена ошибка. Посмотрите загрузочный файл. Name: " + i);
                    flag = false;
                }

                String id = cityElement.getElementsByTagName("id").item(0).getTextContent();
                if (!IsInt.isInt(id)) {
                    System.out.println("Неверный id. Значение должно быть больше 0. Посмотрите загрузочный файл. Name: " + i);
                    flag = false;

                }
                else {
                    if (Integer.parseInt(id) <= 0 || id.equals("")) {
                        System.out.println("Неверный id. Значение должно быть больше 0. Посмотрите загрузочный файл. Name: " + i);
                        flag = false;
                    }
                }
                String telephoneCode = cityElement.getElementsByTagName("telephoneCode").item(0).getTextContent();
                if (!IsLong.isLong(telephoneCode)) {
                    System.out.println("Неверный telephoneCode. Значение должно быть больше 0 и меньше 100000. Посмотрите загрузочный файл. Name: " + i);
                    flag = false;
                }
                else {
                    if ((Long.parseLong(telephoneCode) <= 0) || (Long.parseLong(telephoneCode) > 100000) || (telephoneCode).equals("")) {
                        System.out.println("Неверный telephoneCode. Значение должно быть больше 0 и меньше 100000. Посмотрите загрузочный файл. Name: " + i);
                        flag = false;
                    }
                }

                String carcode = cityElement.getElementsByTagName("carcode").item(0).getTextContent();
                if (!IsLong.isLong(carcode)) {
                    System.out.println("Неверный carcode. Значение должно быть больше 0 и меньше 100000, и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
                    flag = false;
                }
                else {
                    if ((Long.parseLong(carcode) <= 0) || (Long.parseLong(carcode) > 100000) || (carcode).equals("")) {
                        System.out.println("Неверный carcode. Значение должно быть больше 0 и меньше 100000, и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
                        flag = false;
                    }
                }

                String population = cityElement.getElementsByTagName("population").item(0).getTextContent();
                if (!IsLong.isLong(population)) {
                    System.out.println("Неверный population. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
                    flag = false;
                }
                else {
                    if ((Long.parseLong(population) <= 0) || (population.equals(""))) {
                        System.out.println("Неверный population. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
                        flag = false;
                    }
                }

                String  area = cityElement.getElementsByTagName("area").item(0).getTextContent();
                if (!IsFloat.isFloat(area)) {
                    System.out.println("Неверный area. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
                    flag = false;
                }
                else {
                    if ((Float.parseFloat(area) <= 0) || (area.equals(""))) {
                        System.out.println("Неверный area. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
                        flag = false;
                    }
                }

                String metersAboveSeaLevel = cityElement.getElementsByTagName("metersAboveSeaLevel").item(0).getTextContent();
                if (!IsDouble.isDouble(metersAboveSeaLevel)) {
                    flag = false;
                    System.out.println("Ошибка в metersAboveSeaLevel. Name: " + i);
                }

                String age = cityElement.getElementsByTagName("age").item(0).getTextContent();
                if (!IsInt.isInt(age)) {
                    System.out.println("Неверный age. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
                    flag = false;
                }

                else {
                    if ((Integer.parseInt(age) <= 0) || (age.equals(""))) {
                        System.out.println("Неверный age. Значение должно быть больше 0 и не может быть пустым. Посмотрите загрузочный файл. Name: " + i);
                        flag = false;

                    }
                }
                String standardOfLiving = cityElement.getElementsByTagName("standardOfLiving").item(0).getTextContent();
                if (!standardOfLiving.equals("ULTRA_HIGH") & !standardOfLiving.equals("HIGH") & !standardOfLiving.equals("MEDIUM") & !standardOfLiving.equals("ULTRA_LOW") & !standardOfLiving.equals("NIGHTMARE")) {
                    System.out.println("Неверный standardOfLiving. Значение должно соответствовать значениям класса enum. Посмотрите загрузочный файл. Name: " + i);
                    flag = false;
                }
                if (flag) {
                    String creationDate = cityElement.getElementsByTagName("creationDate").item(0).getTextContent();
                    Human governor = new Human();
                    governor.setAge(Integer.parseInt(age));
                    // Создаем объект City на основе считанных данных
                    City city = new City();
                    city.setName(name);
                    city.setId(Integer.parseInt(id));
                    city.setTelephoneCode(Long.parseLong(telephoneCode));
                    city.setCarCode(Long.parseLong(carcode));
                    city.setPopulation(Long.parseLong(population));
                    city.setArea(Float.parseFloat(area));
                    city.setMetersAboveSeaLevel(Double.parseDouble(metersAboveSeaLevel));
                    city.setGovernor(governor);
                    city.setStandardOfLiving(StandardOfLiving.valueOf(standardOfLiving));
                    city.setCreationDate(creationDate);
                    cityCollection.add(city);
                }
            }
            System.out.println(cityCollection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCollection() {
        try {
            // Создаем новый документ XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Создаем корневой элемент "cities"
            Element rootElement = document.createElement("cities");
            document.appendChild(rootElement);

            // Для каждого города в коллекции создаем элемент "city" и добавляем его в корневой элемент
            for (City city : cityCollection) {
                Element cityElement = document.createElement("city");
                rootElement.appendChild(cityElement);

                // Добавляем дочерние элементы для каждого поля города
                addElement(document, cityElement, "name", city.getName());
                addElement(document, cityElement, "id", String.valueOf(city.getId()));
                addElement(document, cityElement, "telephoneCode", String.valueOf(city.getTelephoneCode()));
                addElement(document, cityElement, "standardOfLiving", String.valueOf(city.getStandardOfLiving()));
                addElement(document, cityElement, "carcode", String.valueOf(city.getCarCode()));
                addElement(document, cityElement, "population", String.valueOf(city.getPopulation()));
                addElement(document, cityElement, "area", String.valueOf(city.getArea()));
                addElement(document, cityElement, "metersAboveSeaLevel", String.valueOf(city.getMetersAboveSeaLevel()));
                addElement(document, cityElement, "age", String.valueOf(city.getGovernor().getAge()));
                addElement(document, cityElement, "creationDate", String.valueOf(city.getCreationDate()));
            }

            // Сохраняем измененное DOM дерево обратно в XML файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("city_collection.xml"));
            transformer.transform(source, result);

            System.out.println("XML файл успешно сохранен.");
        } catch (Exception e) {
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




