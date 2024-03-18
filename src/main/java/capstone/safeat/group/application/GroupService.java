package capstone.safeat.group.application;

import static capstone.safeat.member.exception.MemberExceptionType.MEMBER_NOT_FOUND;

import capstone.safeat.group.domain.Group;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final MemberRepository memberRepository;
  private final GroupUpdater groupUpdater;

  @Transactional
  public Long createGroup(final Long memberId) {
    final Member creator = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
    final Group newGroup = groupUpdater.saveNewGroupBy(creator);
    return newGroup.getId();
  }

  @Transactional
  public void leaveGroup(final Long memberId, final Long groupId) {
    final Member leaver = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
    groupUpdater.removeMember(groupId, leaver);
  }
}
