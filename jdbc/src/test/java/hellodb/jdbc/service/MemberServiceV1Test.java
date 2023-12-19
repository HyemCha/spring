package hellodb.jdbc.service;

import hellodb.jdbc.connection.ConnectionConst;
import hellodb.jdbc.domain.Member;
import hellodb.jdbc.repository.MemberRepositoryV1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static hellodb.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;

/**
 * 기본 동작, 트랜잭션이 없어서 문제 발생
 */
class MemberServiceV1Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV1 memberRepositoryV1;
    private MemberServiceV1 memberServiceV1;

    @BeforeEach
    void before() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        memberRepositoryV1 = new MemberRepositoryV1(dataSource);
        memberServiceV1 = new MemberServiceV1(memberRepositoryV1);
    }

    @AfterEach
    void after() throws SQLException {
        memberRepositoryV1.delete(MEMBER_A);
        memberRepositoryV1.delete(MEMBER_B);
        memberRepositoryV1.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {
        //given
        Member a = new Member(MEMBER_A, 10000);
        Member b = new Member(MEMBER_B, 10000);
        memberRepositoryV1.save(a);
        memberRepositoryV1.save(b);

        //when
        memberServiceV1.accountTransfer(a.getMemberId(), b.getMemberId(), 2000);

        //then
        Member findMemberA = memberRepositoryV1.findById(a.getMemberId());
        Member findMemberB = memberRepositoryV1.findById(b.getMemberId());

        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void accountTransferEx() throws SQLException {
        //given
        Member a = new Member(MEMBER_A, 10000);
        Member ex = new Member(MEMBER_EX, 10000);
        memberRepositoryV1.save(a);
        memberRepositoryV1.save(ex);

        //when
        assertThatThrownBy(() -> memberServiceV1.accountTransfer(a.getMemberId(), ex.getMemberId(), 2000))
                .isInstanceOf(IllegalStateException.class);

        //then
        Member findMemberA = memberRepositoryV1.findById(a.getMemberId());
        Member findMemberB = memberRepositoryV1.findById(ex.getMemberId());

        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(10000);
    }
}