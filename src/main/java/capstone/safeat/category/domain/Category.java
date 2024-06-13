package capstone.safeat.category.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum Category {

  FRUITS(1L, "과일", "Fruits", null, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/fruits.png"),
  NUTS(2L, "견과", "Nuts", null, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/nuts.png"),
  VEGETABLES(3L, "채소", "Vegetables", null, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/vegetable.png"),
  MEATS(5L, "고기", "Meat", null, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/pig.png"),
  GRAIN(41L, "곡식", "Grain", null, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/grains.png"),
  EGGS(6L, "계란", "Eggs", null, true,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/egg.png"),
  SEA_FOODS(7L, "어패류", "Seafood", null, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/seafood.png"),
  SEASONINGS(8L, "조미료", "Seasonings", null, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/seasoning.png"),
  DAIRIES(9L, "유제품", "Dairy", null, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/milk.png"),

  APPLE(11L, "사과", "Apple", FRUITS, true, FRUITS.imageUrl),
  KIWI(12L, "키위", "Kiwi", FRUITS, true, FRUITS.imageUrl),
  PEACH(13L, "복숭아", "Peach", FRUITS, true, FRUITS.imageUrl),
  BANANA(14L, "바나나", "Banana", FRUITS, true, FRUITS.imageUrl),
  MANGO(15L, "망고", "Mango", FRUITS, true, FRUITS.imageUrl),
  OTHER_FRUITS(16L, "기타 과일류", "Other fruits", FRUITS, true, FRUITS.imageUrl),

  WALNUT(17L, "호두", "Walnut", NUTS, true, NUTS.imageUrl),
  ALMOND(18L, "아몬드", "Almond", NUTS, true, NUTS.imageUrl),
  PISTACHIO(19L, "피스타치오", "Pistachio", NUTS, true, NUTS.imageUrl),
  HAZELNUT(20L, "헤이즐넛", "Hazelnut", NUTS, true, NUTS.imageUrl),
  PINE_NUTS(21L, "잣", "Pinenuts", NUTS, true, NUTS.imageUrl),
  PEANUT(22L, "땅콩", "Peanut", NUTS, true, NUTS.imageUrl),
  OTHER_NUTS(23L, "기타 견과류", "Other nuts", NUTS, true, NUTS.imageUrl),

  HERBAGE_CROP(24L, "잎줄기 채소", "herbage crop", VEGETABLES, false, VEGETABLES.imageUrl),
  ROOT_VEGETABLES(25L, "뿌리 채소", "Root Vegetables", VEGETABLES, false, VEGETABLES.imageUrl),
  FRUITING_VEGETABLES(26L, "과일 채소", "Fruiting Vegetables", VEGETABLES, false, VEGETABLES.imageUrl),

  ONION(27L, "양파", "Onion", HERBAGE_CROP, true, VEGETABLES.imageUrl),
  GARLIC(28L, "마늘", "Garlic", HERBAGE_CROP, true, VEGETABLES.imageUrl),
  GREEN_ONION(29L, "파", "Green onion", HERBAGE_CROP, true, VEGETABLES.imageUrl),
  CHIVES(30L, "부추", "Chives", HERBAGE_CROP, true, VEGETABLES.imageUrl),
  OTHER_HERBAGE_CROP(31L, "기타 잎줄기 채소", "Other herbage crop", HERBAGE_CROP, true,
      VEGETABLES.imageUrl),

  POTATO(32L, "감자", "Potato", ROOT_VEGETABLES, true, VEGETABLES.imageUrl),
  SWEET_POTATO(33L, "고구마", "Sweet potato", ROOT_VEGETABLES, true, VEGETABLES.imageUrl),
  RADISH(34L, "무", "Radish", ROOT_VEGETABLES, true, VEGETABLES.imageUrl),
  WILD_CHIVE(35L, "달래", "wild chive", ROOT_VEGETABLES, true, VEGETABLES.imageUrl),
  OTHER_ROOT_VEGETABLES(36L, "기타 뿌리 채소", "Other root vegetables", ROOT_VEGETABLES, true,
      VEGETABLES.imageUrl),

  CUCUMBER(37L, "오이", "Cucumber", FRUITING_VEGETABLES, true, VEGETABLES.imageUrl),
  CHILLY(38L, "고추", "Chilly", FRUITING_VEGETABLES, true, VEGETABLES.imageUrl),
  TOMATO(39L, "토마토", "Tomato", FRUITING_VEGETABLES, true, VEGETABLES.imageUrl),
  OTHER_FRUIT_VEGETABLES(40L, "기타 열매 채소", "Other Fruiting Vegetables", FRUITING_VEGETABLES, true,
      VEGETABLES.imageUrl),

  RICE(42L, "쌀", "Rice", GRAIN, true, GRAIN.imageUrl),
  WHEAT(43L, "밀", "Wheat", GRAIN, true, GRAIN.imageUrl),
  BARLEY(90L, "보리", "Barley", GRAIN, true, GRAIN.imageUrl),
  CORN(44L, "옥수수", "Corn", GRAIN, true, GRAIN.imageUrl),
  BUCKWHEAT(45L, "메밀", "Buckwheat", GRAIN, true, GRAIN.imageUrl),
  BEANS(46L, "콩", "Beans", GRAIN, true, GRAIN.imageUrl),
  OTHER_GRAINS(47L, "기타 곡류", "Other Grains", GRAIN, true, GRAIN.imageUrl),

  BEEF(49L, "소고기", "Beef", MEATS, true,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/cow.png"),
  PORK(50L, "돼지고기", "Pork", MEATS, true,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/pig.png"),
  POULTRY(51L, "가금류", "Poultry", MEATS, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/chicken.png"),
  LAMB(52L, "양고기", "Lamb", MEATS, true,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/sheep.png"),
  HORSE_MEAT(53L, "말고기", "Horse Meat", MEATS, true,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/horse.png"),
  CHICKEN(54L, "닭고기", "Chicken", POULTRY, true,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/chicken.png"),
  DUCK(55L, "오리고기", "Duck", POULTRY, true,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/duck.png"),

  FISH(58L, "생선", "fish", SEA_FOODS, false,
      "https://raw.githubusercontent.com/Capstone-2024-1/backend/main/default_image/category/fish.png"),
  MACKEREL(63L, "고등어", "Mackerel", FISH, true, FISH.imageUrl),
  OTHER_FISH(64L, "기타 생선", "Other Fish", FISH, true, FISH.imageUrl),

  OTHER_MOLLUSKS(59L, "기타 연체류", "Other Mollusks", SEA_FOODS, true, SEA_FOODS.imageUrl),

  CRUSTACEANS(60L, "갑각류", "Crustaceans", SEA_FOODS, false, SEA_FOODS.imageUrl),
  SHRIMP(65L, "새우", "Shrimp", CRUSTACEANS, true, SEA_FOODS.imageUrl),
  CRAB(66L, "게", "Crab", CRUSTACEANS, true, SEA_FOODS.imageUrl),
  OTHER_CRUSTACEANS(67L, "기타 갑각류", "Other Crustaceans", CRUSTACEANS, true, SEA_FOODS.imageUrl),

  SHELLFISH(61L, "조개류", "Shellfish", SEA_FOODS, false, SEA_FOODS.imageUrl),
  ABALONE(68L, "전복", "Abalone", SHELLFISH, true, SEA_FOODS.imageUrl),
  OYSTER(69L, "굴", "Oyster", SHELLFISH, true, SEA_FOODS.imageUrl),
  MUSSEL(70L, "홍합", "Mussel", SHELLFISH, true, SEA_FOODS.imageUrl),
  OTHER_SHELLFISH(71L, "기타 조개류", "Other Shellfish", SHELLFISH, true, SEA_FOODS.imageUrl),

  OTHER_SEA_FOODS(62L, "기타 어패류", "Other seafood", SEA_FOODS, true, SEA_FOODS.imageUrl),

  PEPPER(73L, "고추", "Pepper", SEASONINGS, true, SEASONINGS.imageUrl),
  GINGER(74L, "생강", "Ginger", SEASONINGS, true, SEASONINGS.imageUrl),
  HONEY(75L, "꿀", "Honey", SEASONINGS, true, SEASONINGS.imageUrl),
  ASAFOETIDA(83L, "홍거", "Asafoetida", SEASONINGS, true, SEASONINGS.imageUrl),
  OTHER_SEASONINGS(76L, "기타 양념류", "Other seasonings", SEASONINGS, true, SEASONINGS.imageUrl),

  MILK(79L, "우유", "Milk", DAIRIES, true, DAIRIES.imageUrl),
  CHEESE(80L, "치즈", "Cheese", DAIRIES, true, DAIRIES.imageUrl),
  BUTTER(81L, "버터", "Butter", DAIRIES, true, DAIRIES.imageUrl),
  SOY_MILK(90L, "두유", "SOY_MILK", DAIRIES, true, DAIRIES.imageUrl),
  OTHER_DAIRY_PRODUCTS(82L, "기타 유제품", "Other dairy products", DAIRIES, true, DAIRIES.imageUrl);

  private final Long id;
  private final String koreanName;
  private final String englishName;
  private final Category parent;
  private final boolean isLeaf;
  private final String imageUrl;

  Category(
      final Long id, final String koreanName, final String englishName, final Category parent,
      final boolean isLeaf, final String imageUrl
  ) {
    this.id = id;
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.parent = parent;
    this.isLeaf = isLeaf;
    this.imageUrl = imageUrl;
  }

  public static Category fromEnglishName(final String englishName) {
    return Arrays.stream(Category.values())
        .filter(c -> c.getEnglishName().equalsIgnoreCase(englishName))
        .findAny()
        .orElseThrow();
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

  public List<Category> getAllLeafChildren() {
    return getAllChildren().stream()
        .filter(c -> c.isLeaf)
        .toList();
  }

  public List<Category> getAllParent() {
    final List<Category> parents = new ArrayList<>();
    Optional<Category> parent = getParent();
    while (parent.isPresent()) {
      final Category parentCategory = parent.get();
      parents.add(parentCategory);
      parent = parentCategory.getParent();
    }
    return parents;
  }

  public boolean isLeaf() {
    return isLeaf;
  }
}
