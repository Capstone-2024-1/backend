package capstone.safeat.group.application;

import static capstone.safeat.member.exception.MemberExceptionType.MEMBER_NOT_FOUND;

import capstone.safeat.group.domain.GroupDomain;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.member.dto.JwtMemberId;
import capstone.safeat.member.exception.MemberException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final MemberRepository memberRepository;
  private final GroupUpdater groupUpdater;

  @Transactional
  public Long createGroup(final Long memberId, final String groupName) {
    final Member creator = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
    final GroupDomain groupDomain = groupUpdater.saveNewGroupBy(creator, groupName);
    return groupDomain.getId();
  }

  @Transactional
  public void leaveGroup(final Long memberId, final Long groupId) {
    final Member leaver = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
    groupUpdater.removeMember(groupId, leaver);
  }

  @Transactional(readOnly = true)
  public List<GroupDomain> findParticipatedGroups(final JwtMemberId jwtMemberId) {
    return null;
  }
}
