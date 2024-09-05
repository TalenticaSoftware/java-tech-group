package com.tal;

import com.tal.client.UserClient;

public class BasicHttpCodeApplication {

  public static void main(String[] args) {

      UserClient userClient = new UserClient();

      String response = userClient.getUsers();

      System.out.println("Response : " + response);
  }
}