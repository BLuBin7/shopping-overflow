import Header from "@/app/component/Header/Header";
import '../app/styles/index.css'
import '../app/styles/style.css'
import HeaderNav from "@/app/component/Header/HeaderNav";
import Footer from "@/app/component/Footer/Footer";

export default function Home() {
  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
        {/* HEADER TOP */}
        <Header />

        <Footer/>

    </div>
  );
}
