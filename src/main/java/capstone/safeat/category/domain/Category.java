package capstone.safeat.category.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum Category {

  FRUITS(1L, "과일", "Fruits", null),
  NUTS(2L, "견과", "Nuts", null),
  VEGETABLES(3L, "채소", "Vegetables", null),
  MEATS(5L, "고기", "Meat", null),
  EGGS(6L, "계란", "Eggs", null),
  SEA_FOODS(7L, "어패류", "Seafood", null),
  SEASONINGS(8L, "조미료", "Seasonings", null),
  DAIRIES(9L, "유제품", "Dairy", null),

  APPLE(11L, "사과", "Apple", FRUITS),
  KIWI(12L, "키위", "Kiwi", FRUITS),
  PEACH(13L, "복숭아", "Peach", FRUITS),
  BANANA(14L, "바나나", "Banana", FRUITS),
  MANGO(15L, "망고", "Mango", FRUITS),
  OTHER_FRUITS(16L, "기타 과일류", "Other fruits", FRUITS),

  WALNUT(17L, "호두", "Walnut", NUTS),
  ALMOND(18L, "아몬드", "Almond", NUTS),
  PISTACHIO(19L, "피스타치오", "Pistachio", NUTS),
  HAZELNUT(20L, "헤이즐넛", "Hazelnut", NUTS),
  PINE_NUTS(21L, "잣", "Pinenuts", NUTS),
  PEANUT(22L, "땅콩", "Peanut", NUTS),
  OTHER_NUTS(23L, "기타 견과류", "Other nuts", NUTS),

  HERBAGE_CROP(24L, "잎줄기 채소", "herbage crop", VEGETABLES),
  ROOT_VEGETABLES(25L, "뿌리 채소", "Root Vegetables", VEGETABLES),
  FRUITING_VEGETABLES(26L, "과일 채소", "Fruiting Vegetables", VEGETABLES),

  ONION(27L, "양파", "Onion", HERBAGE_CROP),
  GARLIC(28L, "마늘", "Garlic", HERBAGE_CROP),
  GREEN_ONION(29L, "파", "Green onion", HERBAGE_CROP),
  CHIVES(30L, "부추", "Chives", HERBAGE_CROP),
  OTHER_HERBAGE_CROP(31L, "기타 잎줄기 채소", "Other herbage crop", HERBAGE_CROP),

  POTATO(32L, "감자", "Potato", ROOT_VEGETABLES),
  SWEET_POTATO(33L, "고구마", "Sweet potato", ROOT_VEGETABLES),
  RADISH(34L, "무", "Radish", ROOT_VEGETABLES),
  WILD_CHIVE(35L, "달래", "wild chive", ROOT_VEGETABLES),
  OTHER_ROOT_VEGETABLES(36L, "기타 뿌리 채소", "Other root vegetables", ROOT_VEGETABLES),

  CUCUMBER(37L, "오이", "Cucumber", FRUITING_VEGETABLES),
  CHILLY(38L, "고추", "Chilly", FRUITING_VEGETABLES),
  TOMATO(39L, "토마토", "Tomato", FRUITING_VEGETABLES),
  OTHER_FRUIT_VEGETABLES(40L, "기타 열매 채소", "Other fruit vegetables", FRUITING_VEGETABLES),

  GRAIN(41L, "곡식", "Grain", null),
  RICE(42L, "쌀", "Rice", GRAIN),
  WHEAT(43L, "밀", "Wheat", GRAIN),
  BARLEY(90L, "보리", "Barley", GRAIN),
  CORN(44L, "옥수수", "Corn", GRAIN),
  BUCKWHEAT(45L, "메밀", "Buckwheat", GRAIN),
  BEANS(46L, "콩", "Beans", GRAIN),
  OTHER_GRAINS(47L, "기타 곡류", "Other Grains", GRAIN),

  BEEF(49L, "소고기", "Beef", MEATS),
  PORK(50L, "돼지고기", "Pork", MEATS),
  POULTRY(51L, "가금류", "Poultry", MEATS),
  LAMB(52L, "양고기", "Lamb", MEATS),
  HORSE_MEAT(53L, "말고기", "Horse Meat", MEATS),
  CHICKEN(54L, "닭고기", "Chicken", POULTRY),
  DUCK(55L, "오리고기", "Duck", POULTRY),

  FISH(58L, "생선", "fish", SEA_FOODS),
  MACKEREL(63L, "고등어", "Mackerel", FISH),
  OTHER_FISH(64L, "기타 생선", "Other Fish", FISH),

  OTHER_MOLLUSKS(59L, "기타 연체류", "Other Mollusks", SEA_FOODS),

  CRUSTACEANS(60L, "갑각류", "Crustaceans", SEA_FOODS),
  SHRIMP(65L, "새우", "Shrimp", CRUSTACEANS),
  CRAB(66L, "게", "Crab", CRUSTACEANS),
  OTHER_CRUSTACEANS(67L, "기타 갑각류", "Other Crustaceans", CRUSTACEANS),

  SHELLFISH(61L, "조개류", "Shellfish", SEA_FOODS),
  ABALONE(68L, "전복", "Abalone", SHELLFISH),
  OYSTER(69L, "굴", "Oyster", SHELLFISH),
  MUSSEL(70L, "홍합", "Mussel", SHELLFISH),
  OTHER_SHELLFISH(71L, "기타 조개류", "Other Shellfish", SHELLFISH),

  OTHER_SEA_FOODS(62L, "기타 어패류", "Other seafood", SEA_FOODS),

  PEPPER(73L, "고추", "Pepper", SEASONINGS),
  GINGER(74L, "생강", "Ginger", SEASONINGS),
  HONEY(75L, "꿀", "Honey", SEASONINGS),
  ASAFOETIDA(83L, "홍거", "Asafoetida", SEASONINGS),
  OTHER_SEASONINGS(76L, "기타 양념류", "Other seasonings", SEASONINGS),

  MILK(79L, "우유", "Milk", DAIRIES),
  CHEESE(80L, "치즈", "Cheese", DAIRIES),
  BUTTER(81L, "버터", "Butter", DAIRIES),
  OTHER_DAIRY_PRODUCTS(82L, "기타 유제품", "Other dairy products", DAIRIES);

  private final Long id;
  private final String koreanName;
  private final String englishName;
  private final Category parent;

  Category(
      final Long id, final String koreanName, final String englishName,
      final Category parent
  ) {
    this.id = id;
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.parent = parent;
  }

  public static List<Category> readAllById(final List<Long> ids) {
    return Arrays.stream(Category.values())
        .filter(c -> ids.contains(c.getId()))
        .toList();
  }

  public boolean isRootCategory() {
    return parent == null;
  }

  public Optional<Category> getParent() {
    return Optional.ofNullable(parent);
  }

  public List<Category> getAllChildren() {
    return Arrays.stream(Category.values())
        .filter(c -> c.parent == this)
        .toList();
  }
}
