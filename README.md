`@Deprecated`

![photo_2020-10-01_14-34-55](https://user-images.githubusercontent.com/32682273/95655604-4979ca00-0b11-11eb-919c-596bb6d3cb0b.jpg)

# MvpCurrencyConverter
Simple currency converter app, which demonstrates MVP implementation in android

<details>
  <summary>App preview (gif)</summary>
 

![Screen-Recording-20200531-195807](https://user-images.githubusercontent.com/32682273/83385760-7810c700-a3f2-11ea-97cf-15d991549d37.gif)

</details>

<details>
  <summary>Classes diagram</summary>
  
> - [Activity](https://github.com/s95ammar/MvpCurrencyConverter/tree/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/ui/activity)
> - [Base](https://github.com/s95ammar/MvpCurrencyConverter/tree/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/ui/base)

> - [Home](https://github.com/s95ammar/MvpCurrencyConverter/tree/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/ui/home)
> - [Currencies List](https://github.com/s95ammar/MvpCurrencyConverter/tree/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/ui/currencieslist)
> - [Settings](https://github.com/s95ammar/MvpCurrencyConverter/tree/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/ui/settings)

![Untitled Diagram](https://user-images.githubusercontent.com/32682273/83918719-fcac6e00-a781-11ea-8eae-c61cb74c4923.png)

</details>

##### ▼ Tests

> To test the presenters in isolation (so that the tests don't depend on internet connection and server functionality), we provide a [`FakeRepository`](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/test/java/com/s95ammar/mvpcurrencyconverter/model/FakeRepository.kt) (a test-double for the real [`Repository`](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/model/Repository.kt)) to the presenter in the tests. This is done by __*dependency injection*__ and __*inversion of control*__ with the help of [`IRepository`](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/model/IRepository.kt) interface.


Class | Test class
------------ | -------------
[HomePresenter](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/ui/home/HomePresenter.kt) | [HomePresenterTest](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/test/java/com/s95ammar/mvpcurrencyconverter/ui/home/HomePresenterTest.kt)
[CurrenciesListPresenter](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/ui/currencieslist/CurrenciesListPresenter.kt) | [CurrenciesListPresenterTest](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/test/java/com/s95ammar/mvpcurrencyconverter/ui/currencieslist/CurrenciesListPresenterTest.kt)
[SettingsPresenter](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/main/java/com/s95ammar/mvpcurrencyconverter/ui/settings/SettingsPresenter.kt) | [SettingsPresenterTest](https://github.com/s95ammar/MvpCurrencyConverter/blob/master/app/src/test/java/com/s95ammar/mvpcurrencyconverter/ui/settings/SettingsPresenterTest.kt)

![image](https://user-images.githubusercontent.com/32682273/83919227-d0452180-a782-11ea-9c39-7dcded57e70b.png)


