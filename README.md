# お絵描きチャレンジアプリ

初めて触ったスプリングブートでお絵描きアプリ(笑)を作ってみたンゴ 🍊

## 概要

お絵描きチャレンジアプリはランダムで指定されたお題に対して絵を描いて保存できるウェブアプリですう

## 機能

- ユーザー認証(未実装、今後追加)
- ランダムお題の表示(随時追加)
- お絵描き機能
- 絵の保存機能
- ギャラリーの表示

## 使用技術

<p>
<img src="https://img.shields.io/badge/react-blue.svg?logo=react&style=for-the-badge">
<img src="https://img.shields.io/badge/axios-5A29E4.svg?logo=axios&style=for-the-badge">
<img src="https://img.shields.io/badge/Kotlin-gray.svg?logo=Kotlin&style=for-the-badge">
<img src="https://img.shields.io/badge/postgresql-white.svg?logo=postgresql&style=for-the-badge">
<img src="https://img.shields.io/badge/vite-yellow.svg?logo=vite&style=for-the-badge">
</p>
<p>
<img src="https://yamada-ui.com/logo-white.png" width="20%">
</p>

### フロントエンドのセットアップ

1. リポジトリをクローンします。

   ```bash
   git clone git@github.com:kodai-yamada-423/Spring-boot_test.git
   cd pictureApp/Front
   ```

2. 依存関係をインストールします。

   ```bash
   npm install
   ```

3. 開発サーバーを起動します。
   ```bash
   npm start
   ```

### バックエンドのセットアップ

1. リポジトリをクローンします（上記と同様）。

2. プロジェクトディレクトリに移動します。

   ```bash
   cd pictureApp/Back
   ```

3. `application.properties`を設定します。`src/main/resources/application.properties`にデータベース接続情報を追加します。

   ```properties
   　　spring.application.name=pictureApp
   　　spring.datasource.url=jdbc:postgresql://localhost/yourdatabase
   　　spring.datasource.username=yourusername
   　　spring.datasource.password=yourpassword
   　　spring.datasource.driverClassName=org.postgresql.Driver
   ```

4. プロジェクトをビルドして実行します。
   ```bash
   　　./gradlew bootRun
   ```

### データベースのセットアップ

1. PostgreSQL をインストールし、データベースを作成します。
   ```sql
   CREATE DATABASE yourdatabase;
   ```

## 使用方法

1. フロントエンドとバックエンドのサーバーが正常に起動していることを確認します。
2. ブラウザで `http://localhost:5173` にアクセスします。
3. 画面上でお題に沿って絵を描き、保存ボタンをクリックして絵を保存します。
4. 保存した絵はギャラリーで閲覧できます。

## プロジェクトの構成

pictureApp/
├── Front/ # フロントエンドのソースコード
├── Back/ # バックエンドのソースコード
└── README.md # プロジェクトの説明
