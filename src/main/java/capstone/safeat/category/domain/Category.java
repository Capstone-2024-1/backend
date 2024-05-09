package capstone.safeat.category.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum Category {

  FRUITS(1L, "과일", "Fruits", null, false),
  NUTS(2L, "견과", "Nuts", null, false),
  VEGETABLES(3L, "채소", "Vegetables", null, false),
  MEATS(5L, "고기", "Meat", null, false),
  GRAIN(41L, "곡식", "Grain", null, false),
  EGGS(6L, "계란", "Eggs", null, true),
  SEA_FOODS(7L, "어패류", "Seafood", null, false),
  SEASONINGS(8L, "조미료", "Seasonings", null, false),
  DAIRIES(9L, "유제품", "Dairy", null, false),

  APPLE(11L, "사과", "Apple", FRUITS, true),
  KIWI(12L, "키위", "Kiwi", FRUITS, true),
  PEACH(13L, "복숭아", "Peach", FRUITS, true),
  BANANA(14L, "바나나", "Banana", FRUITS, true),
  MANGO(15L, "망고", "Mango", FRUITS, true),
  OTHER_FRUITS(16L, "기타 과일류", "Other fruits", FRUITS, true),

  WALNUT(17L, "호두", "Walnut", NUTS, true),
  ALMOND(18L, "아몬드", "Almond", NUTS, true),
  PISTACHIO(19L, "피스타치오", "Pistachio", NUTS, true),
  HAZELNUT(20L, "헤이즐넛", "Hazelnut", NUTS, true),
  PINE_NUTS(21L, "잣", "Pinenuts", NUTS, true),
  PEANUT(22L, "땅콩", "Peanut", NUTS, true),
  OTHER_NUTS(23L, "기타 견과류", "Other nuts", NUTS, true),

  HERBAGE_CROP(24L, "잎줄기 채소", "herbage crop", VEGETABLES, false),
  ROOT_VEGETABLES(25L, "뿌리 채소", "Root Vegetables", VEGETABLES, false),
  FRUITING_VEGETABLES(26L, "과일 채소", "Fruiting Vegetables", VEGETABLES, false),

  ONION(27L, "양파", "Onion", HERBAGE_CROP, true),
  GARLIC(28L, "마늘", "Garlic", HERBAGE_CROP, true),
  GREEN_ONION(29L, "파", "Green onion", HERBAGE_CROP, true),
  CHIVES(30L, "부추", "Chives", HERBAGE_CROP, true),
  OTHER_HERBAGE_CROP(31L, "기타 잎줄기 채소", "Other herbage crop", HERBAGE_CROP, true),

  POTATO(32L, "감자", "Potato", ROOT_VEGETABLES, true),
  SWEET_POTATO(33L, "고구마", "Sweet potato", ROOT_VEGETABLES, true),
  RADISH(34L, "무", "Radish", ROOT_VEGETABLES, true),
  WILD_CHIVE(35L, "달래", "wild chive", ROOT_VEGETABLES, true),
  OTHER_ROOT_VEGETABLES(36L, "기타 뿌리 채소", "Other root vegetables", ROOT_VEGETABLES, true),

  CUCUMBER(37L, "오이", "Cucumber", FRUITING_VEGETABLES, true),
  CHILLY(38L, "고추", "Chilly", FRUITING_VEGETABLES, true),
  TOMATO(39L, "토마토", "Tomato", FRUITING_VEGETABLES, true),
  OTHER_FRUIT_VEGETABLES(40L, "기타 열매 채소", "Other fruit vegetables", FRUITING_VEGETABLES, true),

  RICE(42L, "쌀", "Rice", GRAIN, true),
  WHEAT(43L, "밀", "Wheat", GRAIN, true),
  BARLEY(90L, "보리", "Barley", GRAIN, true),
  CORN(44L, "옥수수", "Corn", GRAIN, true),
  BUCKWHEAT(45L, "메밀", "Buckwheat", GRAIN, true),
  BEANS(46L, "콩", "Beans", GRAIN, true),
  OTHER_GRAINS(47L, "기타 곡류", "Other Grains", GRAIN, true),

  BEEF(49L, "소고기", "Beef", MEATS, true),
  PORK(50L, "돼지고기", "Pork", MEATS, true),
  POULTRY(51L, "가금류", "Poultry", MEATS, false),
  LAMB(52L, "양고기", "Lamb", MEATS, true),
  HORSE_MEAT(53L, "말고기", "Horse Meat", MEATS, true),
  CHICKEN(54L, "닭고기", "Chicken", POULTRY, true),
  DUCK(55L, "오리고기", "Duck", POULTRY, true),

  FISH(58L, "생선", "fish", SEA_FOODS, false),
  MACKEREL(63L, "고등어", "Mackerel", FISH, true),
  OTHER_FISH(64L, "기타 생선", "Other Fish", FISH, true),

  OTHER_MOLLUSKS(59L, "기타 연체류", "Other Mollusks", SEA_FOODS, true),

  CRUSTACEANS(60L, "갑각류", "Crustaceans", SEA_FOODS, false),
  SHRIMP(65L, "새우", "Shrimp", CRUSTACEANS, true),
  CRAB(66L, "게", "Crab", CRUSTACEANS, true),
  OTHER_CRUSTACEANS(67L, "기타 갑각류", "Other Crustaceans", CRUSTACEANS, true),

  SHELLFISH(61L, "조개류", "Shellfish", SEA_FOODS, false),
  ABALONE(68L, "전복", "Abalone", SHELLFISH, true),
  OYSTER(69L, "굴", "Oyster", SHELLFISH, true),
  MUSSEL(70L, "홍합", "Mussel", SHELLFISH, true),
  OTHER_SHELLFISH(71L, "기타 조개류", "Other Shellfish", SHELLFISH, true),

  OTHER_SEA_FOODS(62L, "기타 어패류", "Other seafood", SEA_FOODS, true),

  PEPPER(73L, "고추", "Pepper", SEASONINGS, true),
  GINGER(74L, "생강", "Ginger", SEASONINGS, true),
  HONEY(75L, "꿀", "Honey", SEASONINGS, true),
  ASAFOETIDA(83L, "홍거", "Asafoetida", SEASONINGS, true),
  OTHER_SEASONINGS(76L, "기타 양념류", "Other seasonings", SEASONINGS, true),

  MILK(79L, "우유", "Milk", DAIRIES, true),
  CHEESE(80L, "치즈", "Cheese", DAIRIES, true),
  BUTTER(81L, "버터", "Butter", DAIRIES, true),
  OTHER_DAIRY_PRODUCTS(82L, "기타 유제품", "Other dairy products", DAIRIES, true);

  private final Long id;
  private final String koreanName;
  private final String englishName;
  private final Category parent;
  private final boolean isLeaf;

  Category(final Long id, final String koreanName, final String englishName, final Category parent,
      final boolean isLeaf) {
    this.id = id;
    this.koreanName = koreanName;
    this.englishName = englishName;
    this.parent = parent;
    this.isLeaf = isLeaf;
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
