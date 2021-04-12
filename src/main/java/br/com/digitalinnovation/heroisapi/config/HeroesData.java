package br.com.digitalinnovation.heroisapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import static br.com.digitalinnovation.heroisapi.constans.HeroesConst.ENDPOINT_DYNAMO;
import static br.com.digitalinnovation.heroisapi.constans.HeroesConst.REGION_DYNAMO;


public class HeroesData {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Heroes_Api_table");

        Item hero = new Item()
                .withPrimaryKey("id","4")
                .withString("name", "Capitao America")
                .withString("universe", "dc comics")
                .withNumber("qttyFilms", 10);
        table.putItem(hero);

        Item hero1 = new Item()
                .withPrimaryKey("id","2")
                .withString("name", "Huck")
                .withString("universe", "dc comics")
                .withNumber("qttyFilms", 3);
        table.putItem(hero1);

        Item hero2 = new Item()
                .withPrimaryKey("id","1")
                .withString("name", "Homen de Aço")
                .withString("universe", "dc comics")
                .withNumber("qttyFilms", 3);
        table.putItem(hero2);

    }
}
