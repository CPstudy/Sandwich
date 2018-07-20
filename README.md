# Sandwich

안드로이드 기본 토스트 메시지에 커스텀 뷰와 애니메이션을 사용한 커스텀 토스트 메시지입니다. 토스트 빵 사이에 각종 내용물을 넣으면 샌드위치가 만들어지는 것에서 이름을 지었습니다.

![](https://github.com/CPstudy/Sandwich/blob/master/images/preview.gif)

## 적용

프로젝트 `build.gradle`에 아래처럼 추가합니다(모듈 `build.gradle`이 아닙니다).

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

### Dependency

모듈 `build.gradle`에 아래처럼 추가합니다.

```
dependencies {
    ...
    implementation 'com.github.CPstudy:Sandwich:0.0.1'
}
```

## 사용법

> minSdkVersion = 14
>
> 아이스크림 샌드위치부터 사용할 수 있습니다.

안드로이드 기본 토스트 메시지와 똑같지만 `Toast` 대신 `Sandwich`를 사용하면 됩니다.

```java
Sandwich.makeText(getApplicationContext(), "Hello, world!", Sandwich.LENGTH_SHORT).show();
```

------

### 타입 지정

샌드위치에서는 4가지의 기본 타입을 제공합니다.

![](https://github.com/CPstudy/Sandwich/blob/master/images/preview2.jpg)

왼쪽 위부터 NORMAL, DONE, WARNING, ERROR.



타입을 사용하기 위해서는 다음과 같이 인스턴스를 생성해야합니다.

```java
Sandwich sandwichDone = new Sandwich(getApplicationContext());
         sandwichDone.setType(Sandwich.SANDWICH_DONE);
         sandwichDone.setDuration(Sandwich.LENGTH_SHORT);
         sandwichDone.setText("샌드위치 주문이 완료되었습니다.");
         sandwichDone.show();
```

`setType(int type)` 메서드를 이용해 타입을 지정할 수 있으며 타입의 종류는 다음과 같습니다.

| 타입                        | 의미          |
| --------------------------- | ------------- |
| `Sandwich.SANDWICH_NORMAL`  | 표준(default) |
| `Sandwich.SANDWICH_DONE`    | 성공          |
| `Sandwich.SANDWICH_WARNING` | 경고          |
| `Sandwich.SADNWICH_ERROR`   | 에러          |

------

### 샌드위치 지속 시간

기본적으로 토스트 메시지를 이용했기 때문에 두 가지 지속 시간만 지원합니다.

#### LENGTH_SHORT

`Sandwich.LENGTH_SHORT` 혹은 `Toast.LENGTH_SHORT`

#### LENGTH_LONG

`Sandwich.LENGTH_LONG` 혹은 `Toast.LENGTH_LONG`

------

### 커스터마이징

샌드위치의 아이콘 박스와 아이콘은 원하는대로 바꿀 수 있습니다.

![](https://github.com/CPstudy/Sandwich/blob/master/images/preview3.jpg)

```java
Sandwich sandwichCustom = new Sandwich(getApplicationContext());
         sandwichCustom.setType(Sandwich.SANDWICH_DONE);
         sandwichCustom.setDuration(Sandwich.LENGTH_SHORT);
         sandwichCustom.setText("내가 샌드위치를 만들었습니다.");
         sandwichCustom.setIconBoxColor(Color.parseColor("#8E24AA"));
         sandwichCustom.setIcon(R.drawable.ic_subway_vector);
         sandwichCustom.show();
```

`setIconBoxColor(int color)` 메서드를 이용해 아이콘 박스의 색상을 바꿀 수 있습니다.

`setIcon(int drawable)` 메서드를 이용해 아이콘을 바꿀 수 있습니다.

> 이 메서드를 사용하면 타입에 지정되어 있던 속성들은 무시됩니다.

------

## License

```
Copyright 2018 CPstudy (Kim SeongJae)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

