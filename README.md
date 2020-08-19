# SpringSecurityAndKeycloakSamples
Spring Security &amp; Spring Boot &amp; Keycloak のクライアントとリソースサーバーのサンプル

run keycloak docker 
```bash
docker run -p 8088:8088 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin keycloak-11.0.0-jyh:latest -Djboss.http.port=8088
```

- resource_server_demo
  - リソースサーバーのデモプログラム。
  - [KeycloakとSpringBoot/SecurityでOpenID Connect(リソースサーバー編)](https://qiita.com/kasa_le/items/259792d3ae4df7f1d2b2)記事で作成したプログラムです。
  
- client_demo
  - クライアントアプリケーションのサンプルプログラム。
  - [KeycloakとSpringBoot/SecurityでOpenID Connect(クライアントアプリケーション編)](https://qiita.com/kasa_le/items/8db9f1da1895cee74aa9)記事で作成したプログラムです。

- client_sso_demo
  - リソースサーバーと連携するクライアントアプリケーションのサンプルプログラム。
  - [KeycloakとSpringBoot/SecurityでOpenID Connect(SSO編)](https://qiita.com/kasa_le/items/d87bff758fa3fc44ca45)記事で作成したプログラムです。