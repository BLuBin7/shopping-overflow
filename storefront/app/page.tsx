import Header from "@/component/header/Header";
import '@/styles/index.css'
import '@/styles/style.css'
import Footer from "@/component/footer/Footer";

export default function Home() {
  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
        {/* HEADER TOP */}
        <Header />

        <Footer/>

    </div>
  );
}
