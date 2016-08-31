//código extraído de
//https://bartoszmilewski.com/2014/10/25/dealing-with-benign-data-races-the-c-way/

std::atomic<int> owner = 0;
int count = 0;
std::mutex mtx;

bool TryEnter() {
    if (owner.load(memory_order_relaxed) == std::this_thread::get_id()) {
        count += 1;
        return true;
    }

    if (mtx.try_lock()) {
        owner.store(std::this_thread::get_id(), memory_order_relaxed);
        return true;
    }
    return false;
}

void Exit() {
    if (count != 0) {
        count -= 1;
        return;
    }
    owner.store(0, memory_order_relaxed);
    mtx.unlock();
}