package Hello.core.order;

import Hello.core.annotation.MainDiscountPolicy;
import Hello.core.discount.DiscountPolicy;
//import Hello.core.discount.FixDiscountPolicy;
//import Hello.core.discount.RateDiscountPolicy;
import Hello.core.member.MemberRepository;
//import Hello.core.member.MemoryMemberRepository;
import Hello.core.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
//    private final MemberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

// Lombok: private 보고 생성자 만들어줌
    // 1. 같은 타입의 bean이 두 개 이므로(Fix, Rate), discountPolicy 이름 변경 (discountPolicy -> rateDiscountPolicy)
    // 2. @Qualifier("") 이용, 필드명 바꾸지 않고 옆에 붙여줌
    // 3. @Primary 이용.
    @Autowired //생성자가 하나이므로 Autowired 생략가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired (setter를 이용한 주입)
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired (setter를 이용한 주입)
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
