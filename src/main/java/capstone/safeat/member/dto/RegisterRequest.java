package capstone.safeat.member.dto;

import java.util.List;

public record RegisterRequest(String nickName, List<Long> categoryIds) {

}
