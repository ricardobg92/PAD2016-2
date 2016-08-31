//código extraído de
//https://bartoszmilewski.com/2014/10/25/dealing-with-benign-data-races-the-c-way/

int owner = 0;
int count = 0;
std::mutex mtx;

bool TryEnter() {
    if (owner == std::this_thread::get_id()) {
        count += 1;
        return true;
    }

    if (mtx.try_lock()) {
        owner = std::this_thread::get_id();
        return true;
    }
    return false;
}

void Exit() {
    if (count != 0) {
        count -= 1;
        return;
    }
    owner = 0;
    mtx.unlock();
}